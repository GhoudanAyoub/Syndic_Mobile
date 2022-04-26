package com.syndicg5.networking.repository;


import androidx.lifecycle.LiveData;

import com.syndicg5.networking.di.dao;
import com.syndicg5.networking.models.Login;

import javax.inject.Inject;

import dagger.Reusable;

@Reusable
public class roomRepository {
    private final dao dao;

    @Inject
    public roomRepository(dao dao) {
        this.dao = dao;
    }


    public void saveLogin(Login f) {
        dao.saveLogin(f);
    }

    public void UpdateLogin(Login f) {
        dao.UpdateLogin(f);
    }

    public LiveData<Login> getLoginInfo() {
        return dao.getLoginInfo();
    }

}
