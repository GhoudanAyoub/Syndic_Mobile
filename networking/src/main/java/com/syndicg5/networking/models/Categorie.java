package com.syndicg5.networking.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

public class Categorie implements Serializable {

    private int id;
    private String libelle;
    private Syndic syndic;

    public Categorie(int id, String libelle, Syndic syndic) {
        this.id = id;
        this.libelle = libelle;
        this.syndic = syndic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Syndic getSyndic() {
        return syndic;
    }

    public void setSyndic(Syndic syndic) {
        this.syndic = syndic;
    }
}
