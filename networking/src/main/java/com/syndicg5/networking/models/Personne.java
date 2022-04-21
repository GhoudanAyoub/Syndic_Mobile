package com.syndicg5.networking.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "personne")
public class Personne implements Serializable {


    @PrimaryKey(autoGenerate = true)
    private String id;
    private String nom;
    private String prenom;
    private String email;
    private String motPasse;
    private String adresse;
    private String ville;
    private String photo;
    private String tel;

    public Personne(String nom, String prenom, String email, String motPasse, String adresse, String ville, String photo, String tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motPasse = motPasse;
        this.adresse = adresse;
        this.ville = ville;
        this.photo = photo;
        this.tel = tel;
    }

    public Personne(String id, String nom, String prenom, String email, String motPasse, String adresse, String ville, String photo, String tel) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motPasse = motPasse;
        this.adresse = adresse;
        this.ville = ville;
        this.photo = photo;
        this.tel = tel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotPasse() {
        return motPasse;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
