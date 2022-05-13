package com.syndicg5.networking.api;

import androidx.lifecycle.LiveData;

import com.syndicg5.networking.models.Appartement;
import com.syndicg5.networking.models.Depense;
import com.syndicg5.networking.models.Immeuble;
import com.syndicg5.networking.models.Resident;
import com.syndicg5.networking.models.Revenu;
import com.syndicg5.networking.models.Syndic;

import java.util.List;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APISettings {


    @POST("login")
    Single<Response<ResponseBody>> Login(@Query("username") String username, @Query("password") String password);

    //Syndic
    @GET("syndics")
    Single<List<Syndic>> getAllSyndic();

    @GET("syndics/{id}")
    Single<Syndic> getOneSyndic(@Path("id") int id);

    //residents
    @GET("residents")
    Single<List<Resident>> getAllResidents();

    @GET("residents/{id}")
    Single<Resident> getOneResidents(@Path("id") int id);

    //Immeuble
    @POST("immeubles")
    Single<Response<ResponseBody>> addImmeuble(@Body Immeuble immeuble);

    @GET("immeubles")
    Single<List<Immeuble>> getAllImmeuble();

    @GET("immeubles/{id}")
    Single<Immeuble> getOneImmeuble(@Path("id") int id);

    //Appartement
    @POST("appartements")
    Single<Response<ResponseBody>> addAppartements(@Body Appartement appartement);

    @GET("appartements")
    Single<List<Appartement>> getAllAppartements();

    @GET("appartements/{id}")
    Single<Appartement> getOneAppartement(@Path("id") int id);

    @GET("appartements/syndic/immeuble/{id}")
    Single<List<Appartement>> getAppartementByImmeuble(@Path("id") int id);

    //revenu
    @GET("revenusByAppartement/{id}")
    Single<List<Revenu>> getRevenusByAppartement(@Path("id") int id);

    @GET("revenusByAppartement/{id}")
    LiveData<List<Revenu>> getRevenusByAppartementData(@Path("id") int id);

    @GET("revenusByImmeuble/{id}")
    Single<List<Revenu>> getRevenusByImmeuble(@Path("id") int id);

    //depense
    @GET("depenseByImmeuble/{id}")
    Single<List<Depense>> getDepenseByImmeuble(@Path("id") int id);


}
