package com.hsapi.apideck.exception;

import com.hsapi.apideck.model.Deck;
import com.hsapi.apideck.repo.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

public class DeckNotFoundException extends RuntimeException {

    public DeckNotFoundException(Integer id) {
        super("Could not find deck " + id);
    }
}

@Component
class DeckCommandLineRunner implements CommandLineRunner {
    @Autowired
    DeckRepository deckRepository;

    @Override
    public void run(String... args) throws Exception{
        for (Deck d : this.deckRepository.findAll()){
            System.out.println(d.toString());
        }
    }


}


