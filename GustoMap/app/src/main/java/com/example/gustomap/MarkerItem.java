package com.example.gustomap;

public class MarkerItem {
    double lat;
    double lon;
    String name;
    String link;


    public MarkerItem(double lat, double lon,String name, String link) {
        this.lat = lat;
        this.lon = lon;
        this.link = link;
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
