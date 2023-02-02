package com.hsapi.apideck.repo;

import org.springframework.data.repository.CrudRepository;

import com.hsapi.apideck.model.Deck;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface DeckRepository extends CrudRepository<Deck, Integer> {

}