package com.syndicg5.networking.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "immeuble")
public class Immeuble implements Serializable {


    @PrimaryKey(autoGenerate = true)
    private String id;
    private String nom;
    private String adresse;
    private String ville;
    private String photo;
    private Integer numero;
    private Integer etages;
    private Syndic syndic;
    private List<Appartement> appartements;
    private List<Depense> depenses;
    private List<Revenu> revenus;

    public Immeuble(String id, String nom, String adresse, String ville, String photo, Integer numero, Integer etages, Syndic syndic, List<Appartement> appartements, List<Depense> depenses, List<Revenu> revenus) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.ville = ville;
        this.photo = photo;
        this.numero = numero;
        this.etages = etages;
        this.syndic = syndic;
        this.appartements = appartements;
        this.depenses = depenses;
        this.revenus = revenus;
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

    public void setSyndic(Syndic syndic) {
        this.syndic = syndic;
    }

    public List<Appartement> getAppartements() {
        return appartements;
    }

    public void setAppartements(List<Appartement> appartements) {
        this.appartements = appartements;
    }

    public List<Depense> getDepenses() {
        return depenses;
    }

    public void setDepenses(List<Depense> depenses) {
        this.depenses = depenses;
    }

    public List<Revenu> getRevenus() {
        return revenus;
    }

    public void setRevenus(List<Revenu> revenus) {
        this.revenus = revenus;
    }
}
