package com.hsapi.apideck.exception;


import com.hsapi.apideck.model.Carte;
import com.hsapi.apideck.repo.CarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

public class CarteNotFoundException extends RuntimeException {

    public CarteNotFoundException(Integer id) {
        super("Could not find carte " + id);
    }
}

@Component
class CarteCommandLineRunner implements CommandLineRunner {
    @Autowired
    CarteRepository carteRepository;

    @Override
    public void run(String... args) throws Exception{
        for (Carte c : this.carteRepository.findAll()){
            System.out.println(c.toString());
        }
    }


}
