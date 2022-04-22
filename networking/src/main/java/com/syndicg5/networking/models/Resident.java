package com.syndicg5.networking.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity(tableName = "resident")
public class Resident implements Serializable {


    @PrimaryKey(autoGenerate = true)
    private String id;
    private String etat_familiale;
    private Personne personne;
    private List<Appartement> appartements;

    public Resident(String etat_familiale, Personne personne, List<Appartement> appartements) {
        this.etat_familiale = etat_familiale;
        this.personne = personne;
        this.appartements = appartements;
    }

    public Resident(String id, String etat_familiale, Personne personne, List<Appartement> appartements) {
        this.id = id;
        this.etat_familiale = etat_familiale;
        this.personne = personne;
        this.appartements = appartements;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEtat_familiale() {
        return etat_familiale;
    }

    public void setEtat_familiale(String etat_familiale) {
        this.etat_familiale = etat_familiale;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public List<Appartement> getAppartements() {
        return appartements;
    }

    public void setAppartements(List<Appartement> appartements) {
        this.appartements = appartements;
    }
}
