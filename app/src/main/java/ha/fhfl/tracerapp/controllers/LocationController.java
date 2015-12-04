package ha.fhfl.tracerapp.controllers;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import ha.fhfl.tracerapp.MainActivity;
import ha.fhfl.tracerapp.models.Model;

public class LocationController implements LocationListener {

    private MainActivity activity;
    protected LocationManager locationManager;
    private Model model;

    private String TAG = "fhflMyLocationListener";

    public LocationController(MainActivity activity, Model model){
        this.activity = activity;
        this.model = model;
        init();
    }

    public void init() {
        Intent startIntent = new Intent("android.location.GPS_ENABLED_CHANGE");
        startIntent.putExtra("enabled", true);


        activity.sendBroadcast(startIntent);
        locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        } catch (SecurityException e) {

        }
    }

    public void onLocationChanged(Location location) {
        model.setLocation(location);
        activity.upDateAll();
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d(TAG + " " + "onStatusChanged", "GPS enabled: " + locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER));
    }

    public void onProviderEnabled(String provider) {
        Log.d(TAG + " " + "onProviderEnabled", "GPS enabled: " + locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER));
    }

    public void onProviderDisabled(String provider) {
        Log.d(TAG + " " + "onProviderEnabled", "GPS disabled: " + locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER));
    }


}