package com.syndicg5.networking.di;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.syndicg5.networking.models.Immeuble;
import com.syndicg5.networking.models.Login;

@Dao
public interface dao {

    @Query("Select * from Login")
    LiveData<Login> getLoginInfo();

    @Insert
    void saveLogin(Login f);

    @Update
    void UpdateLogin(Login f);

    @Query("DELETE FROM immeuble")
    void deleteImmeuble();

    @Query("Select * from immeuble")
    LiveData<Immeuble> getImmeubleInfo();

    @Insert(onConflict = REPLACE)
    void saveImmeuble(Immeuble f);

    @Update
    void UpdateImmeuble(Immeuble f);

}
