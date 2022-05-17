package com.syndicg5.networking.repository;


import androidx.lifecycle.LiveData;

import com.syndicg5.networking.di.dao;
import com.syndicg5.networking.models.Immeuble;
import com.syndicg5.networking.models.Login;
import com.syndicg5.networking.models.User;

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

    public void saveImmeuble(Immeuble f) {
        dao.deleteImmeuble();
        dao.saveImmeuble(f);
    }

    public void saveUser(User f) {
        dao.saveUser(f);
    }

    public void UpdateImmeuble(Immeuble f) {
        dao.UpdateImmeuble(f);
    }
    public void UpdateUser(User f) {
        dao.UpdateUser(f);
    }

    public LiveData<Immeuble> getImmeubleInfo() {
        return dao.getImmeubleInfo();
    }

    public LiveData<User> getUserInfo() {
        return dao.getUserInfo();
    }

}
