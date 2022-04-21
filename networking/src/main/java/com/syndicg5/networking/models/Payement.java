package com.syndicg5.networking.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "payement")
public class Payement implements Serializable {


    @PrimaryKey(autoGenerate = true)
    private String id;
    private Double montant;
    private Date date;
    private String description;

    public Payement(Double montant, Date date, String description) {
        this.montant = montant;
        this.date = date;
        this.description = description;
    }

    public Payement(String id, Double montant, Date date, String description) {
        this.id = id;
        this.montant = montant;
        this.date = date;
        this.description = description;
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
}
