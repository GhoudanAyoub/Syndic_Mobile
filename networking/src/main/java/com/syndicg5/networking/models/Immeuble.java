package com.syndicg5.networking.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "immeuble")
public class Immeuble implements Serializable {


    @PrimaryKey(autoGenerate = true)
    private String id;
    private String libelle;
    private String adresse;
    private String ville;
    private Integer numero;
    private Integer nbEtages;
    private Syndic syndic;
    private List<Depense> depenses;
    private List<Revenu> revenus;

    public Immeuble(String libelle, String adresse, String ville, Integer numero, Integer nbEtages, Syndic syndic) {
        this.libelle = libelle;
        this.adresse = adresse;
        this.ville = ville;
        this.numero = numero;
        this.nbEtages = nbEtages;
        this.syndic = syndic;
    }

    public Immeuble(String libelle, String adresse, String ville, Integer numero, Integer nbEtages, Syndic syndic, List<Depense> depenses, List<Revenu> revenus) {
        this.libelle = libelle;
        this.adresse = adresse;
        this.ville = ville;
        this.numero = numero;
        this.nbEtages = nbEtages;
        this.syndic = syndic;
        this.depenses = depenses;
        this.revenus = revenus;
    }

    public Immeuble(String id, String libelle, String adresse, String ville, Integer numero, Integer nbEtages, Syndic syndic, List<Depense> depenses, List<Revenu> revenus) {
        this.id = id;
        this.libelle = libelle;
        this.adresse = adresse;
        this.ville = ville;
        this.numero = numero;
        this.nbEtages = nbEtages;
        this.syndic = syndic;
        this.depenses = depenses;
        this.revenus = revenus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
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

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getNbEtages() {
        return nbEtages;
    }

    public void setNbEtages(Integer nbEtages) {
        this.nbEtages = nbEtages;
    }

    public Syndic getSyndic() {
        return syndic;
    }

    public void setSyndic(Syndic syndic) {
        this.syndic = syndic;
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

    public void ListRevenus(List<Revenu> revenus) {
        this.revenus = revenus;
    }
}
