package com.syndicg5.networking.request;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.syndicg5.networking.models.Appartement;
import com.syndicg5.networking.models.Immeuble;

public class RevenusReq {

    private Double montant;
    private String description;
    private String date;
    private Immeuble immeuble;
    private Appartement appartement;

    public RevenusReq(Double montant, String description, String date, Immeuble idImmeuble, Appartement idAppatement) {
        this.montant = montant;
        this.description = description;
        this.date = date;
        this.immeuble = idImmeuble;
        this.appartement = idAppatement;
    }

    public Immeuble getImmeuble() {
        return immeuble;
    }

    public void setImmeuble(Immeuble immeuble) {
        this.immeuble = immeuble;
    }

    public Appartement getAppartement() {
        return appartement;
    }

    public void setAppartement(Appartement appartement) {
        this.appartement = appartement;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
