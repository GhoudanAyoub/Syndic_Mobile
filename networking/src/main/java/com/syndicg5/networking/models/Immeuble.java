package com.syndicg5.networking.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "immeuble")
public class Immeuble implements Serializable {

    @PrimaryKey
    private int id;
    private String nom;
    private String adresse;
    private String ville;
    private String photo;
    private Integer numero;
    private Integer etages;
    private Syndic syndic;


    public Immeuble(int id, String nom, String adresse, String ville, String photo, Integer numero, Integer etages, Syndic syndic) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.ville = ville;
        this.photo = photo;
        this.numero = numero;
        this.etages = etages;
        this.syndic = syndic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getEtages() {
        return etages;
    }

    public void setEtages(Integer etages) {
        this.etages = etages;
    }

    public Syndic getSyndic() {
        return syndic;
    }

    public void setSyndic_id(Syndic syndic_id) {
        this.syndic = syndic_id;
    }
}
