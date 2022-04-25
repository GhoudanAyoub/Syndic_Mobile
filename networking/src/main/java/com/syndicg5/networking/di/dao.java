package com.syndicg5.networking.di;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.syndicg5.networking.models.Login;

@Dao
public interface dao {

    @Query("Select * from Login")
    LiveData<Login> getLoginInfo();

    @Insert
    void saveLogin(Login f);

    @Update
    void UpdateLogin(Login f);

}
