package com.syndicg5.networking.repository;


import com.syndicg5.networking.api.APISettings;

import javax.inject.Inject;

import dagger.Reusable;

@Reusable
public class apiRepository {
    private final APISettings apiSettings;

    @Inject
    public apiRepository(APISettings apiSettings) {
        this.apiSettings = apiSettings;
    }


}
