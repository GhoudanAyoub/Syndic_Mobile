package com.syndicg5.networking.api;

import com.syndicg5.networking.models.Appartement;
import com.syndicg5.networking.request.RevenusReq;
import com.syndicg5.networking.models.Categorie;
import com.syndicg5.networking.models.Depense;
import com.syndicg5.networking.models.Immeuble;
import com.syndicg5.networking.models.Resident;
import com.syndicg5.networking.models.Revenu;
import com.syndicg5.networking.models.Syndic;
import com.syndicg5.networking.request.depenseReq;

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
    Single<Response<ResponseBody>> Login(@Query("email") String username, @Query("password") String password);

    //Syndic
    @GET("api/syndics")
    Single<List<Syndic>> getAllSyndic();

    @GET("api/syndicsByEmail/{email}")
    Single<Syndic> getOneSyndic(@Path("email") String id);

    //residents
    @GET("api/residents")
    Single<List<Resident>> getAllResidents();

    @GET("api/residents/{id}")
    Single<Resident> getOneResidents(@Path("id") int id);

    @GET("api/residents/syndic/{id}")
    Single<List<Resident>> getResidentBySyndic(@Path("id") int id);

    @GET("api/residentByEmail/{email}")
    Single<Resident> getOneResident(@Path("email") String id);

    //Immeuble
    @POST("api/immeubles")
    Single<Response<ResponseBody>> addImmeuble(@Body Immeuble immeuble);

    @GET("api/immeubles/syndic/{id}")
    Single<List<Immeuble>> getAllImmeuble(@Path("id") int id);

    @GET("api/immeubles/resident/{email}")
    Single<List<Immeuble>> getImmeubleResident(@Path("email") String id);

    @GET("api/immeubles/{id}")
    Single<Immeuble> getOneImmeuble(@Path("id") int id);

    //Appartement
    @POST("api/appartements")
    Single<Response<ResponseBody>> addAppartements(@Body Appartement appartement);

    @GET("api/appartements")
    Single<List<Appartement>> getAllAppartements();

    @GET("api/appartements/{id}")
    Single<Appartement> getOneAppartement(@Path("id") int id);

    @GET("api/appartements/resident/{id}")
    Single<List<Appartement>> getAppartementByResident(@Path("id") int id);

    @GET("api/appartements/syndic/immeuble/{id}")
    Single<List<Appartement>> getAppartementByImmeuble(@Path("id") int id);

    //revenu
    @GET("api/revenusByAppartement/{id}")
    Single<List<Revenu>> getRevenusByAppartement(@Path("id") int id);

    @GET("api/revenusByAppartement/{id}")
    Single<List<Revenu>> getRevenusByAppartementData(@Path("id") int id);

    @GET("api/revenusByImmeuble/{id}")
    Single<List<Revenu>> getRevenusByImmeuble(@Path("id") int id);

    //depense
    @GET("api/depenseByImmeuble/{id}")
    Single<List<Depense>> getDepenseByImmeuble(@Path("id") int id);


    @POST("/api/revenus")
    Single<Response<ResponseBody>> addRevenus(@Body RevenusReq f);

    @POST("api/depenses")
    Single<Response<ResponseBody>> addDepense(@Body depenseReq f);

    @GET("api/categories")
    Single<List<Categorie>> getAllCategories();
}
