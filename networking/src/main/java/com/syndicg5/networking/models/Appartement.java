package com.syndicg5.networking.models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity(tableName = "appartement")
public class Appartement implements Serializable {

    @PrimaryKey
    private int id;
    private Integer numero;
    private Integer etage;
    private Double surface;
    private Date debut;
    private Date fin;
    private Resident resident;
    private Immeuble immeuble;

    public Appartement(int id, Integer numero, Integer etage, Double surface, Date debut, Date fin, Resident resident, Immeuble immeuble) {
        this.id = id;
        this.numero = numero;
        this.etage = etage;
        this.surface = surface;
        this.debut = debut;
        this.fin = fin;
        this.resident = resident;
        this.immeuble = immeuble;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }
}
