package com.hsapi.apideck.controller;

import java.util.List;

import com.hsapi.apideck.exception.ListDeckNotFoundException;
import com.hsapi.apideck.model.Deck;
import com.hsapi.apideck.model.ListDeck;
import com.hsapi.apideck.repo.ListDeckRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ListDeckController {

    private final ListDeckRepository repository;

    ListDeckController(ListDeckRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/listDecks")
    List<ListDeck> all() {
        return (List<ListDeck>) repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/listDecks")
    ListDeck newListDeck(@RequestBody ListDeck newListDeck) {
        return repository.save(newListDeck);
    }

    // Single item

    @GetMapping("/listDecks/{id}")
    ListDeck one(@PathVariable Integer id) {

        return repository.findById(id)
                .orElseThrow(() -> new ListDeckNotFoundException(id));
    }

    @PutMapping("/listDecks/{id}")
    ListDeck replaceListDeck(@RequestBody ListDeck newListDeck, @PathVariable Integer id) {

        return repository.findById(id)
                .map(listDeck -> {
                    listDeck.setListDeckName(newListDeck.getListDeckName());
                    return repository.save(listDeck);
                })
                .orElseGet(() -> {
                    newListDeck.setListDeckId(id);
                    return repository.save(newListDeck);
                });
    }

    @DeleteMapping("/listDecks/{id}")
    void deleteListDeck(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}