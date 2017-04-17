package com.example.sdj.sherlockmc.service;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.example.sdj.sherlockmc.beans.GPSData;
import com.example.sdj.sherlockmc.utils.Constants;

import java.util.List;
import java.util.Locale;

/**
 * Created by sdj on 4/16/17.
 */

public class GPSService implements LocationListener {

    private LocationManager locManager;
    private Context context;

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public GPSData getLocation() {
        try {
            if (ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                            PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                Log.d(Constants.PERMISSION_DENIED_GPS,Constants.PERMISSION_DENIED_GPS);
                return null;
            }
            Location location = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (true||location == null) {
                double latitutde = location.getLatitude();
                double longitude = location.getLongitude();
                double altitude = location.getAltitude();
                String point = Double.toString(latitutde) + "; " + Double.toString(longitude) + "; " + Double.toString(altitude);
                Log.d(Constants.PASSIVE_POINT, point);
                String cityName = "";
                try {
                    Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                    List<Address> addressList;
                    addressList = geocoder.getFromLocation(latitutde, longitude, 1);
                    cityName = addressList.get(0).getLocality();

                    Log.d(Constants.LOG_CITY_NAME, cityName);
                } catch (Exception e) {
                    Log.d(Constants.ERROR_GPS, e.getMessage());
                }
                return new GPSData(latitutde,longitude,cityName);
            }
        }
        catch (Exception e)
        {
            Log.d(Constants.ERROR_GPS,e.getMessage());
        }
        return null;
    }

    public GPSService(Context context)
    {
        this.context = context;
        locManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }
}
