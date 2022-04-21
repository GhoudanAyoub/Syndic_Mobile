package com.syndicg5.networking.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "categorie")
public class Categorie implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private String id;
    private String libelle;
    private Depense depense;
    private Revenu revenu;

    public Categorie(String libelle, Depense depense, Revenu revenu) {
        this.libelle = libelle;
        this.depense = depense;
        this.revenu = revenu;
    }

    public Categorie(String id, String libelle, Depense depense, Revenu revenu) {
        this.id = id;
        this.libelle = libelle;
        this.depense = depense;
        this.revenu = revenu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Depense getDepense() {
        return depense;
    }

    public void setDepense(Depense depense) {
        this.depense = depense;
    }

    public Revenu getRevenu() {
        return revenu;
    }

    public void setRevenu(Revenu revenu) {
        this.revenu = revenu;
    }
}
