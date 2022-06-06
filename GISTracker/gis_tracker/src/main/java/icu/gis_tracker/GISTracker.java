package icu.gis_tracker;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import org.godotengine.godot.Godot;
import org.godotengine.godot.plugin.GodotPlugin;

import java.util.Arrays;
import java.util.List;

public class GISTracker extends GodotPlugin {

    /*---------- Listener class to get coordinates ------------- */
    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location loc) {
            latitude = loc.getLatitude();
            longitude = loc.getLongitude();
            altitude = loc.getAltitude();
            gps_time = loc.getTime();
            gps_speed = loc.getSpeed();
            gps_bearing = loc.getBearing();
            gps_accuracy = loc.getAccuracy();
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }

    // The minimum distance to change Updates in meters // 1 meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1;

    // The minimum time between updates in milliseconds // 1 sec
    private static final long MIN_TIME_BW_UPDATES = 1000 * 1 * 1;

    // Flag for GPS status
    boolean isGPSEnabled = false;

    // Flag for network status
    boolean isNetworkEnabled = false;

    // Flag for GPS status
    boolean canGetLocation = false;

    // Location
    Location location;

    // Latitude
    private double latitude = 0;

    // Longitude
    private double longitude = 0;

    // Altitude
    private double altitude = 0;

    // Time
    private long gps_time = 0;

    // Speed
    private float gps_speed = 0;

    // Bearing
    private float gps_bearing = 0;

    // Accuracy
    private float gps_accuracy = 0;

    // The main activity of the game
    private Activity activity = null;

    //LocationListener locationListener;
    LocationListener locationListener = new MyLocationListener();

    // Declaring a Location Manager
    protected LocationManager locationManager;

    /* Init
     * ********************************************************************** */

    /**
     * Function to get latitude
     */

    public String getStringLatitude() {
        String value = Double.toString(this.latitude);

        return (value);
    }


    /**
     * Function to get longitude
     * */

    public String getStringLongitude() {
        String value = Double.toString(this.longitude);
        return (value);
    }


    /**
     * Function to get altitude
     * */

    public String getStringAltitude() {
        String value = Double.toString(this.altitude);
        return (value);
    }


    /**
     * Function to get GPS Time
     * */

    public String getStringGPSTime() {
        String value = Long.toString(this.gps_time);
        return (value);
    }


    /**
     * Function to get GPS Speed
     * */

    public String getStringGPSSpeed() {
        String value = Float.toString(this.gps_speed);
        return (value);
    }


    /**
     * Function to get GPS Bearing
     * */

    public String getStringGPSBearing() {
        String value = Float.toString(this.gps_bearing);
        return (value);
    }


    /**
     * Function to get GPS Accuracy
     * */

    public String getStringGPSAccuracy() {
        String value = Float.toString(this.gps_accuracy);
        return (value);
    }


    public String getGPSState() {
        int value = 0;
        if (this.isGPSEnabled) {
            value = 1;
        }
        return (Integer.toString(value));
    }


    public String getNetworkState() {
        int value = 0;
        if (this.isNetworkEnabled) {
            value = 1;
        }
        return (Integer.toString(value));
    }

    public void init() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

                    // Getting GPS status
                    isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

                    // Getting network status
                    isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

                    if (locationManager != null) {
                        canGetLocation = true;

                        if (ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(activity, "First enable LOCATION ACCESS in settings.", Toast.LENGTH_LONG).show();
                            return;
                        }
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        Log.d("LOCATION",String.valueOf(location.getLatitude()));
                        if (location != null)
                        {
                            latitude =  location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }

                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES,
                            locationListener
                    );
                }
                catch (Exception e)
                {
                    //e.printStackTrace();
                    //Log.w("w",e);
                }
            }
        });
    }

    @Nullable
    @Override
    public View onMainCreate(Activity activity) {
        this.activity = activity;
        return super.onMainCreate(activity);
    }

    public GISTracker(Godot godot) {
        super(godot);
    }

    @NonNull
    @Override
    public String getPluginName() {
        return "GISTracker";
    }

    @NonNull
    @Override
    public List<String> getPluginMethods() {
        return Arrays.asList(
                "init",
                "getStringLatitude",
                "getStringLongitude",
                "getStringGPSAccuracy",
                "getStringGPSBearing",
                "getStringGPSSpeed",
                "getStringGPSTime",
                "getStringAltitude",
                "getNetworkState",
                "getGPSState"
        );
    }
}
