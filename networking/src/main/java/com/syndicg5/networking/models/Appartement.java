package com.syndicg5.networking.models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "appartement")
public class Appartement implements Serializable {

    @PrimaryKey
    private String id;
    private Integer numero;
    private Integer etage;
    private Double surface;
    private int resident_id;
    private int immeuble_id;



    public Appartement(Integer numero, Integer etage, Double surface, int resident_id, int immeuble_id) {
        this.numero = numero;
        this.etage = etage;
        this.surface = surface;
        this.resident_id = resident_id;
        this.immeuble_id = immeuble_id;
    }

    public Appartement(String id, Integer numero, Integer etage, Double surface, int resident_id, int immeuble_id) {
        this.id = id;
        this.numero = numero;
        this.etage = etage;
        this.surface = surface;
        this.resident_id = resident_id;
        this.immeuble_id = immeuble_id;
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

    public int getResident_id() {
        return resident_id;
    }

    public void setResident_id(int resident_id) {
        this.resident_id = resident_id;
    }

    public int getImmeuble_id() {
        return immeuble_id;
    }

    public void setImmeuble_id(int immeuble_id) {
        this.immeuble_id = immeuble_id;
    }
}
