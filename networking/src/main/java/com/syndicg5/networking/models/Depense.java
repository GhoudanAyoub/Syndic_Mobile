package com.syndicg5.networking.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity(tableName = "depense")
public class Depense implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private Double montant;
    private Date date;
    private String description;
    private Categorie categories ;
    private Immeuble immeuble;

    public Depense(int id, Double montant, Date date, String description, Categorie categories, Immeuble immeuble) {
        this.id = id;
        this.montant = montant;
        this.date = date;
        this.description = description;
        this.categories = categories;
        this.immeuble = immeuble;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Categorie getCategories() {
        return categories;
    }

    public void setCategories(Categorie categories) {
        this.categories = categories;
    }

    public Immeuble getImmeuble() {
        return immeuble;
    }

    public void setImmeuble(Immeuble immeuble) {
        this.immeuble = immeuble;
    }
}
