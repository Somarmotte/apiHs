package com.hsapi.apideck.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class ListDeck {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer ListDeckId;

    private String ListDeckName;

    public ListDeck(Integer listDeckId, String listDeckName) {
        ListDeckId = listDeckId;
        ListDeckName = listDeckName;
    }

    public ListDeck() {
    }

    public Integer getListDeckId() {
        return ListDeckId;
    }

    public void setListDeckId(Integer listDeckId) {
        ListDeckId = listDeckId;
    }

    public String getListDeckName() {
        return ListDeckName;
    }

    public void setListDeckName(String listDeckName) {
        ListDeckName = listDeckName;
    }
}
