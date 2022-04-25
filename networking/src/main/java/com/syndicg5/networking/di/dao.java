package com.syndicg5.networking.di;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.syndicg5.networking.models.User;

@Dao
public interface dao {

    @Query("Select * from user")
    LiveData<User> getLoginInfo();

    @Insert
    void saveLogin(User f);

    @Update
    void UpdateLogin(User f);

}
