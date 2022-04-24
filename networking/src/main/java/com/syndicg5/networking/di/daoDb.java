package com.syndicg5.networking.di;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.syndicg5.networking.models.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class daoDb extends RoomDatabase {
    public abstract dao dao();
}
