package com.syndicg5.networking.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity(tableName = "revenu")
public class Revenu implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private String id;
    private Double montant;
    private Date date;
    private String description;
    private Appartement appartement;
    private Immeuble immeuble;

    public Revenu(String id, Double montant, Date date, String description, Appartement appartement, Immeuble immeuble) {
        this.id = id;
        this.montant = montant;
        this.date = date;
        this.description = description;
        this.appartement = appartement;
        this.immeuble = immeuble;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Appartement getAppartement() {
        return appartement;
    }

    public void setAppartement(Appartement appartement) {
        this.appartement = appartement;
    }

    public Immeuble getImmeuble() {
        return immeuble;
    }

    public void setImmeuble(Immeuble immeuble) {
        this.immeuble = immeuble;
    }
}
