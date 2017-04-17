package com.example.sdj.sherlockmc.beans;

/**
 * Created by sdj on 4/16/17.
 */

public class GPSData {
    private double latitude;
    private double longitude;
    private String cityName;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCityName() {
        return cityName;
    }

    public GPSData(double latitude, double longitude, String cityName) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.cityName = cityName;
    }
}
