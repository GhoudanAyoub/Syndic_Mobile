package com.syndicg5.networking.repository;


import com.syndicg5.networking.api.APISettings;
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

    public Single<List<Syndic>> getAllSyndic() {
        return apiSettings.getAllSyndic();
    }

    public Single<Syndic> getOneSyndic(int id) {
        return apiSettings.getOneSyndic(id);
    }

}
