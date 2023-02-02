package com.hsapi.apideck.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsapi.apideck.exception.CarteNotFoundException;
import com.hsapi.apideck.model.Carte;
import com.hsapi.apideck.repo.CarteRepository;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

@RestController
class CarteController {

    private final CarteRepository repository;

    CarteController(CarteRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/cartes")
    ArrayList<Carte> all() {

        for(int i=0; i<113; i++) {
            try {
                URL url = new URL("https://us.api.blizzard.com/hearthstone/cards?locale=fr_FR&access_token=EUZTQUCrqqTTs72a21s3n23LUKfe4Btm28");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                int responsecode = conn.getResponseCode();
                if (responsecode != 200) {
                    throw new RuntimeException("HttpResponseCode: " + responsecode);
                } else {
                    StringBuilder inline = new StringBuilder();
                    Scanner scanner = new Scanner(url.openStream());
                    while (scanner.hasNext()) {
                        inline.append(scanner.nextLine());
                    }

                    //Close the scanner
                    scanner.close();

                    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


                    //read JSON file and convert to a customer object
                    Carte carte = objectMapper.readValue(inline.toString(), Carte.class);

                    //print customer details
                    System.out.println(carte.toString());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
            return (ArrayList<Carte>) repository.findAll();
        }

     //
    // end::get-aggregate-root[]

    @PostMapping("/cartes")
    Carte newCarte(@RequestBody Carte newCarte) {
        return repository.save(newCarte);
    }

    // Single item

    @GetMapping("/cartes/{id}")
    Carte one(@PathVariable Integer id) {

        return repository.findById(id)
                .orElseThrow(() -> new CarteNotFoundException(id));
    }

    @PutMapping("/cartes/{id}")
    Carte replaceCarte(@RequestBody Carte newCarte, @PathVariable Integer id) {

        return repository.findById(id)
                .map(carte -> {
                    carte.setName(newCarte.getName());
                    return repository.save(carte);
                })
                .orElseGet(() -> {
                    newCarte.setId(id);
                    return repository.save(newCarte);
                });
    }

    @DeleteMapping("/cartes/{id}")
    void deleteCarte(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}