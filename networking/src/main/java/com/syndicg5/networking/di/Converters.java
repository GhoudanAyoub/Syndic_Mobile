package com.syndicg5.networking.di;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.syndicg5.networking.models.Appartement;
import com.syndicg5.networking.models.Categorie;
import com.syndicg5.networking.models.Depense;
import com.syndicg5.networking.models.Immeuble;
import com.syndicg5.networking.models.Revenu;
import com.syndicg5.networking.models.Syndic;

import java.lang.reflect.Type;
import java.util.List;

public class Converters {
    @TypeConverter
    public String FromSyndicToString(Syndic syndic) {
        return new Gson().toJson(syndic);
    }

    @TypeConverter
    public Syndic FromStringToSyndic(String json) {
        return new Gson().fromJson(json, Syndic.class);
    }

    @TypeConverter
    public String FromImmeubleToString(Immeuble immeuble) {
        return new Gson().toJson(immeuble);
    }

    @TypeConverter
    public Immeuble FromStringToImmeuble(String json) {
        return new Gson().fromJson(json, Immeuble.class);
    }

    @TypeConverter
    public String FromCategorieToString(Categorie categorie) {
        return new Gson().toJson(categorie);
    }

    @TypeConverter
    public Categorie FromStringToCategorie(String json) {
        return new Gson().fromJson(json, Categorie.class);
    }

    @TypeConverter
    public String FromAppartementToString(Appartement appartement) {
        return new Gson().toJson(appartement);
    }

    @TypeConverter
    public Appartement FromStringToAppartement(String json) {
        return new Gson().fromJson(json, Appartement.class);
    }

    @TypeConverter
    public String FromListAppartementToString(List<Appartement> Appartement) {
        return new Gson().toJson(Appartement);
    }


    @TypeConverter
    public List<Appartement> FromStringToListAppartement(String json) {
        if (json == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Appartement>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    @TypeConverter
    public String FromListImmeubleToString(List<Immeuble> Appartement) {
        return new Gson().toJson(Appartement);
    }

    @TypeConverter
    public List<Immeuble> FromStringToListImmeuble(String json) {
        if (json == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Immeuble>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    @TypeConverter
    public String FromListDepenseToString(List<Depense> Appartement) {
        return new Gson().toJson(Appartement);
    }

    @TypeConverter
    public List<Depense> FromStringToListDepense(String json) {
        if (json == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Depense>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    @TypeConverter
    public String FromListRevenuToString(List<Revenu> Appartement) {
        return new Gson().toJson(Appartement);
    }

    @TypeConverter
    public List<Revenu> FromStringToListRevenu(String json) {
        if (json == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Revenu>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

}
