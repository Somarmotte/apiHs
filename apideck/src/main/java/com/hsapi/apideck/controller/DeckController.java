package com.hsapi.apideck.controller;

import java.util.List;

import com.hsapi.apideck.exception.DeckNotFoundException;
import com.hsapi.apideck.model.Deck;
import com.hsapi.apideck.repo.DeckRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class DeckController {

    private final DeckRepository repository;

    DeckController(DeckRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/decks")
    List<Deck> all() {
        return (List<Deck>) repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/decks")
    Deck newDeck(@RequestBody Deck newDeck) {
        return repository.save(newDeck);
    }

    // Single item

    @GetMapping("/decks/{id}")
    Deck one(@PathVariable Integer id) {

        return repository.findById(id)
                .orElseThrow(() -> new DeckNotFoundException(id));
    }

    @PutMapping("/decks/{id}")
    Deck replaceDeck(@RequestBody Deck newDeck, @PathVariable Integer id) {

        return repository.findById(id)
                .map(deck -> {
                    deck.setDeckName(newDeck.getDeckName());
                    deck.setClasse(newDeck.getClasse());
                    return repository.save(deck);
                })
                .orElseGet(() -> {
                    newDeck.setDeckId(id);
                    return repository.save(newDeck);
                });
    }

    @DeleteMapping("/decks/{id}")
    void deleteDeck(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
