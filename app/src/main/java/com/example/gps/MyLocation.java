package com.example.gps;

import android.app.Application;
import android.location.Location;

import java.util.ArrayList;

public class MyLocation extends Application {
    private static MyLocation myLocation;
    private ArrayList<Location> locations;

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }

    public static MyLocation getMyLocation() {
        return myLocation;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        myLocation = this;
        locations = new ArrayList<>();
    }
}
