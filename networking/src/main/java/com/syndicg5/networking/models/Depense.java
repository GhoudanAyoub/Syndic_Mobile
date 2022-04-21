package com.syndicg5.networking.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity(tableName = "depense")
public class Depense implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private String id;
    private Double montant;
    private Date date;
    private String description;
    private List<Categorie> categories ;
    private Immeuble immeuble;

    public Depense(Double montant, Date date, String description, List<Categorie> categories, Immeuble immeuble) {
        this.montant = montant;
        this.date = date;
        this.description = description;
        this.categories = categories;
        this.immeuble = immeuble;
    }

    public Depense(String id, Double montant, Date date, String description, List<Categorie> categories, Immeuble immeuble) {
        this.id = id;
        this.montant = montant;
        this.date = date;
        this.description = description;
        this.categories = categories;
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

    public List<Categorie> getCategories() {
        return categories;
    }

    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
    }

    public Immeuble getImmeuble() {
        return immeuble;
    }

    public void setImmeuble(Immeuble immeuble) {
        this.immeuble = immeuble;
    }
}
