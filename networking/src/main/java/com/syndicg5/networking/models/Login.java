package com.syndicg5.networking.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Login")
public class Login {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private boolean status;

    public Login(int id, boolean status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
