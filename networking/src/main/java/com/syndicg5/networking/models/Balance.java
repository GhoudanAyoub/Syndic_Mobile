package com.syndicg5.networking.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Balance")
public class Balance {

    @PrimaryKey(autoGenerate = true)
    private int userID;
    private Double outcome;
    private Double income;
    private Double solde;
    private String description;
    private long date;
    private User user;

    @Ignore
    public Balance(Double outcome, Double income, long date, User user, String description) {
        this.outcome = outcome;
        this.income = income;
        this.date=date;
        this.user=user;
        this.description=description;
    }
    public Balance(int userID, Double outcome, Double income, long date) {
        this.userID = userID;
        this.outcome = outcome;
        this.income = income;
        this.date=date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Double getOutcome() {
        return outcome;
    }

    public void setOutcome(Double outcome) {
        this.outcome = outcome;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
