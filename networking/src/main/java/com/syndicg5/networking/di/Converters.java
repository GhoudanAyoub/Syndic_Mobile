package com.syndicg5.networking.di;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.syndicg5.networking.models.Appartement;
import com.syndicg5.networking.models.Depense;
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
    public String FromListAppartementToString(List<Appartement> Appartement) {
        return new Gson().toJson(Appartement);
    }

    @TypeConverter
    public List<Appartement> FromStringToListAppartement(String json) {
        return new Gson().fromJson(json, (Type) Appartement.class);
    }
    @TypeConverter
    public String FromListDepenseToString(List<Depense> Appartement) {
        return new Gson().toJson(Appartement);
    }

    @TypeConverter
    public List<Depense> FromStringToListDepense(String json) {
        return new Gson().fromJson(json, (Type) Depense.class);
    }

    @TypeConverter
    public String FromListRevenuToString(List<Revenu> Appartement) {
        return new Gson().toJson(Appartement);
    }

    @TypeConverter
    public List<Revenu> FromStringToListRevenu(String json) {
        return new Gson().fromJson(json, (Type) Revenu.class);
    }

}
