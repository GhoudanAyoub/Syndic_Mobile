package com.syndicg5.networking.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Login")
public class Login {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private boolean status;
    private int type;

    public Login(int id, boolean status, int type) {
        this.id = id;
        this.status = status;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
