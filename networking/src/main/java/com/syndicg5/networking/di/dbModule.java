package com.syndicg5.networking.di;
import android.app.Application;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class dbModule {

    @Provides
    @Singleton
    public static daoDb ProvideDb(Application application){
        return Room.databaseBuilder(
                application
                , daoDb.class
                ,"DaoDB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }


    @Provides
    @Singleton
    public static dao ProvideDao(daoDb daoDB){
        return daoDB.dao();
    }
}