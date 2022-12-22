package com.syndicg5.networking.models;

public class Pitches {
    private long pitchId;

    private String name;

    private int capacity;

    private double price;

    private String location;

    private User owner;

    private String photo;

    public Pitches(long pitchId, String name, int capacity, double price, String location, String photo) {
        this.pitchId = pitchId;
        this.name = name;
        this.capacity = capacity;
        this.price = price;
        this.location = location;
        this.photo = photo;
    }

    public Pitches(long pitchId, String name, int capacity, double price, String location, User owner, String photo) {
        this.pitchId = pitchId;
        this.name = name;
        this.capacity = capacity;
        this.price = price;
        this.location = location;
        this.owner = owner;
        this.photo = photo;
    }

    public long getPitchId() {
        return pitchId;
    }

    public void setPitchId(long pitchId) {
        this.pitchId = pitchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
