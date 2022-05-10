package com.syndicg5.networking.repository;


import com.syndicg5.networking.api.APISettings;
import com.syndicg5.networking.models.Appartement;
import com.syndicg5.networking.models.Depense;
import com.syndicg5.networking.models.Immeuble;
import com.syndicg5.networking.models.Resident;
import com.syndicg5.networking.models.Revenu;
import com.syndicg5.networking.models.Syndic;
import com.syndicg5.networking.models.User;

import java.util.List;

import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;

@Reusable
public class apiRepository {
    private final APISettings apiSettings;

    @Inject
    public apiRepository(APISettings apiSettings) {
        this.apiSettings = apiSettings;
    }


    public Single<Response<ResponseBody>> Login(User user) {
        return apiSettings.Login(user.getLogin(), user.getPassword());
    }


    //Syndic
    public Single<List<Syndic>> getAllSyndic() {
        return apiSettings.getAllSyndic();
    }

    public Single<Syndic> getOneSyndic(int id) {
        return apiSettings.getOneSyndic(id);
    }

    //Resident
    public Single<List<Resident>> getAllResidents() {
        return apiSettings.getAllResidents();
    }

    public Single<Resident> getOneResidents(int id) {
        return apiSettings.getOneResidents(id);
    }

    //Appartement
    public Single<Response<ResponseBody>> addAppartements(Appartement appartement) {
        return apiSettings.addAppartements(appartement);
    }

    public Single<List<Appartement>> getAllAppartements() {
        return apiSettings.getAllAppartements();
    }

    public Single<Appartement> getOneAppartement(int id) {
        return apiSettings.getOneAppartement(id);
    }

    public Single<List<Appartement>> getAppartementByImmeuble(int id) {
        return apiSettings.getAppartementByImmeuble(id);
    }

    //revenu  depense
    public Single<List<Revenu>> getRevenusByAppartement(int id) {
        return apiSettings.getRevenusByAppartement(id);
    }
    public Single<List<Revenu>> getRevenusByImmeuble(int id) {
        return apiSettings.getRevenusByImmeuble(id);
    }
    public Single<List<Depense>> getDepenseByImmeuble(int id) {
        return apiSettings.getDepenseByImmeuble(id);
    }

    //Immeuble

    public Single<Response<ResponseBody>> addImmeuble(Immeuble immeuble) {
        return apiSettings.addImmeuble(immeuble);
    }

    public Single<List<Immeuble>> getAllImmeuble() {
        return apiSettings.getAllImmeuble();
    }

    public Single<Immeuble> getOneImmeuble(int id) {
        return apiSettings.getOneImmeuble(id);
    }

}
