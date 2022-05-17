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
import com.syndicg5.networking.models.User;

@Dao
public interface dao {

    @Query("Select * from Login")
    LiveData<Login> getLoginInfo();

    @Insert(onConflict = REPLACE)
    void saveLogin(Login f);

    @Query("DELETE FROM Login")
    void deleteLogin();

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

    @Insert(onConflict = REPLACE)
    void saveUser(User u);
    @Query("DELETE FROM user")
    void deleteUser();

    @Query("Select * from user")
    LiveData<User> getUserInfo();

    @Update
    void UpdateUser(User f);

}
