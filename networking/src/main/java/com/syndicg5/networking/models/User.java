package com.syndicg5.networking.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "user")
public class User implements Serializable {

    @PrimaryKey
    private int userId;
    private String email;
    private String password;
    private String fullname;
    private String photo;
    private String telephone;
    private int type;
    private boolean verified;

    @Ignore
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Ignore
    public User(String email, String password, String fullname,  int type) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.photo = "";
        this.telephone = "";
        this.type = type;
        this.verified = true;
    }

    public User(int userId, String email, String password, String fullname, String photo, String telephone, int type) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.photo = photo;
        this.telephone = telephone;
        this.type = type;
        this.verified = true;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
