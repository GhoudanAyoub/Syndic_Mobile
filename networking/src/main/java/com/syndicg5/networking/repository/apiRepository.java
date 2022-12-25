package com.syndicg5.networking.repository;


import com.syndicg5.networking.api.APISettings;
import com.syndicg5.networking.models.Appartement;
import com.syndicg5.networking.models.Categorie;
import com.syndicg5.networking.models.Depense;
import com.syndicg5.networking.models.Immeuble;
import com.syndicg5.networking.models.Pitches;
import com.syndicg5.networking.models.Reservation;
import com.syndicg5.networking.models.Resident;
import com.syndicg5.networking.models.Revenu;
import com.syndicg5.networking.models.Syndic;
import com.syndicg5.networking.models.User;
import com.syndicg5.networking.request.RevenusReq;
import com.syndicg5.networking.request.depenseReq;

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

    public Single<Response<ResponseBody>> Login(String email, String password) {
        return apiSettings.Login(email, password);
    }

    public Single<Response<ResponseBody>> createAccount(User user) {
        return apiSettings.createAccount(user);
    }
    public Single<Response<ResponseBody>> RunReservation(Reservation reservation) {
        return apiSettings.RunReservation(reservation);
    }

    public Single<User> getUserServerInfo(String email) {
        return apiSettings.getUserServerInfo(email);
    }
    public Single<List<Reservation>> getReservationByUserId(int id) {
        return apiSettings.getReservationByUserId(id);
    }
    //Syndic
    public Single<List<Syndic>> getAllSyndic() {
        return apiSettings.getAllSyndic();
    }

    public Single<Syndic> getOneSyndic(String id) {
        return apiSettings.getOneSyndic(id);
    }

    //Resident
    public Single<List<Resident>> getAllResidents() {
        return apiSettings.getAllResidents();
    }

    public Single<Resident> getOneResidents(String id) {
        return apiSettings.getOneResident(id);
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

    public Single<List<Appartement>> getAppartementByResident(int id) {
        return apiSettings.getAppartementByResident(id);
    }

    public Single<List<Appartement>> getAppartementByImmeuble(int id) {
        return apiSettings.getAppartementByImmeuble(id);
    }

    //revenu  depense
    public Single<List<Revenu>> getRevenusByAppartement(int id) {
        return apiSettings.getRevenusByAppartement(id);
    }

    public Single<List<Revenu>> getRevenusByAppartementData(int id) {
        return apiSettings.getRevenusByAppartementData(id);
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

    public Single<List<Immeuble>> getAllImmeuble(int id, String email, int type) {
        if (type == 1)
            return apiSettings.getAllImmeuble(id);
        else
            return apiSettings.getImmeubleResident(email);
    }

    public Single<Immeuble> getOneImmeuble(int id) {
        return apiSettings.getOneImmeuble(id);
    }

    public Single<List<Pitches>> getAllPitches() {
        return apiSettings.getAllPitches();
    }

    public Single<Response<ResponseBody>> saveBalance(RevenusReq f, depenseReq d, boolean type) {
        if (type)
            return apiSettings.addRevenus(f);
        else {
           return apiSettings.addDepense(d);
        }
    }

    public  Single<List<Categorie>> getAllCategories() {
        return apiSettings.getAllCategories();
    }

    public Single<List<Revenu>> getPaymentByResident(int id) {
        return apiSettings.getPaymentByResident(id);
    }
}
