package com.syndicg5.networking.request;

import com.syndicg5.networking.models.Categorie;
import com.syndicg5.networking.models.Immeuble;

public class depenseReq {

    private Double montant;
    private String description;
    private String date;
    private Immeuble immeuble;
    private Categorie categorie;

    public depenseReq(Double montant, String description, String date, Immeuble idImmeuble, Categorie categorie) {
        this.montant = montant;
        this.description = description;
        this.date = date;
        this.immeuble = idImmeuble;
        this.categorie = categorie;
    }

    public Immeuble getImmeuble() {
        return immeuble;
    }

    public void setImmeuble(Immeuble immeuble) {
        this.immeuble = immeuble;
    }


    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
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
