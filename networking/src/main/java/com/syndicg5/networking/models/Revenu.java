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
    private List<Categorie> categories;

    public Revenu(Double montant, Date date, String description, List<Categorie> categories) {
        this.montant = montant;
        this.date = date;
        this.description = description;
        this.categories = categories;
    }

    public Revenu(String id, Double montant, Date date, String description, List<Categorie> categories) {
        this.id = id;
        this.montant = montant;
        this.date = date;
        this.description = description;
        this.categories = categories;
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
}
