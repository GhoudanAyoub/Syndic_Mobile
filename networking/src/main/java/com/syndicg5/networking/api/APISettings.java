package com.syndicg5.networking.api;

import com.syndicg5.networking.models.Syndic;
import com.syndicg5.networking.models.User;

import java.util.List;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APISettings {


    @POST("login")
    Single<Response<ResponseBody>> Login(@Query("username") String username, @Query("password") String password);

    @GET("syndics")
    Single<List<Syndic>> getAllSyndic();

    @GET("machines/byMarque")
    Single<List<byMarque>> getByMarque();

    @GET("machines/byYearApi")
    Single<List<byMarque>> getByYearApi();
}
