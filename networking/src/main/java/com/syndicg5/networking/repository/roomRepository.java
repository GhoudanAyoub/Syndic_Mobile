package com.syndicg5.networking.repository;


import androidx.lifecycle.LiveData;

import com.syndicg5.networking.di.dao;
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

    public LiveData<User> getLoginInfo() {
        return dao.getLoginInfo();
    }

}
