package com.example.acer.terminalsapp;

/**
 * Created by Acer on 20.06.2016.
 */
public class Location {
    int id;
    String lat;
    String longt;

    public Location() {
    }

    public Location(int id, String lat, String longt) {
        this.id = id;
        this.lat = lat;
        this.longt = longt;
    }

    public Location(String lat, String longt) {
        this.longt = longt;
        this.lat = lat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongt() {
        return longt;
    }

    public void setLongt(String longt) {
        this.longt = longt;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", lat='" + lat + '\'' +
                ", longt='" + longt + '\'' +
                '}';
    }
}
