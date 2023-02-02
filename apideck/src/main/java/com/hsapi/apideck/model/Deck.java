package com.hsapi.apideck.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.*;

@Entity
public class Deck {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer DeckId;

    private String DeckName;

    private String Classe;

    public Deck(Integer deckId, String deckName, String classe) {
        DeckId = deckId;
        DeckName = deckName;
        Classe = classe;
    }

    public Deck() {
    }

    public Integer getDeckId() {
        return DeckId;
    }

    public void setDeckId(Integer deckId) {
        DeckId = deckId;
    }

    public String getDeckName() {
        return DeckName;
    }

    public void setDeckName(String deckName) {
        DeckName = deckName;
    }

    public String getClasse() {
        return Classe;
    }

    public void setClasse(String classe) {
        Classe = classe;
    }
}
