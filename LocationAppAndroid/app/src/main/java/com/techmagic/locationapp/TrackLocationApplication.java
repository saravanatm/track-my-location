package com.techmagic.locationapp;

import android.app.Application;
import android.location.Location;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.location.LocationRequest;
import com.techmagic.locationapp.data.model.LocationData;
import io.fabric.sdk.android.Fabric;

public class TrackLocationApplication extends Application {

    private LocationRequestData locationRequestData;
    private Location startLocation;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        setLocationRequestData(LocationRequestData.FREQUENCY_MEDIUM);
        initializeDB();
    }

    public void setLocationRequestData(LocationRequestData requestData) {
        locationRequestData = requestData;
    }

    public LocationRequestData getLocationRequestData() {
        return locationRequestData;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public LocationRequest createLocationRequest() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(locationRequestData.getInterval());
        locationRequest.setFastestInterval(locationRequestData.getFastestInterval());
        locationRequest.setPriority(locationRequestData.getPriority());
        locationRequest.setSmallestDisplacement(locationRequestData.getSmallestDisplacement());
        return locationRequest;
    }

    protected void initializeDB() {
        Configuration.Builder configurationBuilder = new Configuration.Builder(this);
        configurationBuilder.addModelClasses(LocationData.class);
        ActiveAndroid.initialize(configurationBuilder.create());
    }

}
