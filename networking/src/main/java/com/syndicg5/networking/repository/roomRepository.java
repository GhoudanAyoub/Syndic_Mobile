package com.syndicg5.networking.repository;


import com.syndicg5.networking.di.dao;

import javax.inject.Inject;

public class roomRepository {
    private final com.syndicg5.networking.di.dao dao;

    @Inject
    public roomRepository(dao dao) {
        this.dao = dao;
    }

}
