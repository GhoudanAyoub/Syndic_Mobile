package com.syndicg5.networking.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity(tableName = "resident")
public class Resident implements Serializable {


    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private String adresse;
    private String ville;
    private String photo;
    private String telephone;
    private List<Appartement> appartements;
    private int type ;

    public User toUser(){
        return new User( id,  email,  mdp,  nom,  prenom,  mdp,  adresse,  ville,  photo,  telephone,type);
    }

    public Resident(int id, String nom, String prenom, String email, String mdp, String adresse, String ville, String photo, String telephone, List<Appartement> appartements) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.adresse = adresse;
        this.ville = ville;
        this.photo = photo;
        this.telephone = telephone;
        this.appartements = appartements;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Appartement> getAppartements() {
        return appartements;
    }

    public void setAppartements(List<Appartement> appartements) {
        this.appartements = appartements;
    }
}
