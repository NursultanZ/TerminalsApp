package com.example.acer.terminalsapp;

/**
 * Created by Acer on 13.06.2016.
 */
public class Point {
    int id;
    String point_name;
    String lat;
    String longt;

    public Point(){
    }

    public Point(String point_name, String lat, String longt) {
        this.point_name = point_name;
        this.lat = lat;
        this.longt = longt;
    }

    public Point(int id, String point_name, String lat, String longt) {
        this.id = id;
        this.point_name = point_name;
        this.lat = lat;
        this.longt = longt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoint_name() {
        return point_name;
    }

    public void setPoint_name(String point_name) {
        this.point_name = point_name;
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
        return "Point{" +
                "id=" + id +
                ", point_name='" + point_name + '\'' +
                ", lat='" + lat + '\'' +
                ", longt='" + longt + '\'' +
                '}';
    }
}
