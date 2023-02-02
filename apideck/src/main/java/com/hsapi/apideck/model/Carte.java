package com.hsapi.apideck.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Carte {

    @Id @GeneratedValue
    private Integer id;

    private String name;

    public Carte(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Carte() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
