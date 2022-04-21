package com.syndicg5.networking.di;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {}, version = 1, exportSchema = false)
public abstract class daoDb extends RoomDatabase {
    public abstract dao dao();
}
