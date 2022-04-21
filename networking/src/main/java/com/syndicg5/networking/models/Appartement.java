package com.syndicg5.networking.models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "appartement")
public class Appartement implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private String id;
    private Integer numero;
    private Integer etage;
    private Double surface;
    private List<Payement> payements;
    private Resident resident;
    private Immeuble immeuble;

    public Appartement(Integer numero, Integer etage, Double surface, Immeuble immeuble) {
        this.numero = numero;
        this.etage = etage;
        this.surface = surface;
        this.immeuble = immeuble;
    }

    public Appartement(Integer numero, Integer etage, Double surface, List<Payement> payements, Resident resident, Immeuble immeuble) {
        this.numero = numero;
        this.etage = etage;
        this.surface = surface;
        this.payements = payements;
        this.resident = resident;
        this.immeuble = immeuble;
    }

    public Appartement(String id, Integer numero, Integer etage, Double surface, List<Payement> payements, Resident resident, Immeuble immeuble) {
        this.id = id;
        this.numero = numero;
        this.etage = etage;
        this.surface = surface;
        this.payements = payements;
        this.resident = resident;
        this.immeuble = immeuble;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getEtage() {
        return etage;
    }

    public void setEtage(Integer etage) {
        this.etage = etage;
    }

    public Double getSurface() {
        return surface;
    }

    public void setSurface(Double surface) {
        this.surface = surface;
    }

    public List<Payement> getPayements() {
        return payements;
    }

    public void setPayements(List<Payement> payements) {
        this.payements = payements;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }

    public Immeuble getImmeuble() {
        return immeuble;
    }

    public void setImmeuble(Immeuble immeuble) {
        this.immeuble = immeuble;
    }
}
