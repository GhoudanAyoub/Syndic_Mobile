package com.syndicg5.networking.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "syndic")
public class Syndic implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private String id;
    private Double salaire;
    private Personne personne;
    private List<Immeuble> immeubles;

    public Syndic(Double salaire, Personne personne, List<Immeuble> immeubles) {
        this.salaire = salaire;
        this.personne = personne;
        this.immeubles = immeubles;
    }

    public Syndic(String id, Double salaire, Personne personne, List<Immeuble> immeubles) {
        this.id = id;
        this.salaire = salaire;
        this.personne = personne;
        this.immeubles = immeubles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getSalaire() {
        return salaire;
    }

    public void setSalaire(Double salaire) {
        this.salaire = salaire;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public List<Immeuble> getImmeubles() {
        return immeubles;
    }

    public void setImmeubles(List<Immeuble> immeubles) {
        this.immeubles = immeubles;
    }
}
