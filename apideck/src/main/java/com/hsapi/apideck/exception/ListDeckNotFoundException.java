package com.hsapi.apideck.exception;

import com.hsapi.apideck.model.ListDeck;
import com.hsapi.apideck.repo.ListDeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

public class ListDeckNotFoundException extends RuntimeException {

    public ListDeckNotFoundException(Integer id) {
        super("Could not find listDeck " + id);
    }
}

@Component
class ListDeckCommandLineRunner implements CommandLineRunner {
    @Autowired
    ListDeckRepository listDeckRepository;

    @Override
    public void run(String... args) throws Exception{
        for (ListDeck l : this.listDeckRepository.findAll()){
            System.out.println(l.toString());
        }
    }


}
