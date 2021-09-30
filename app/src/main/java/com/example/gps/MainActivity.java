package com.example.gps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_FINE_LOCATION = 50;
    private static final int DEFAULT_UPDATE_INTERVAL = 30;
    private static final int Fast_UPDATE_INTERVAL = 5;

    private TextView lat_value, lon_value, altitude_value, accuracy_value, speed_value, update_value, sensor_value, address_value, way_points_value;
    private SwitchCompat save_power, location_updates;
    private Button new_point_list, show_way_points_list, show_map;

    private LocationRequest lr;
    private FusedLocationProviderClient flpc;
    private LocationCallback lc;

    private Location currentLocation;
    private ArrayList<Location> locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        initializeElements();

        locations = new ArrayList<>();

        lr = LocationRequest.create();
        setLocationRequestValues();

        lc = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);

                updateUIElements(locationResult.getLastLocation());
            }
        };

        setListeners();

        updateGPS();
    }

    private void initializeElements() {
        lat_value = findViewById(R.id.lat_value);
        lon_value = findViewById(R.id.lon_value);
        altitude_value = findViewById(R.id.altitude_value);
        accuracy_value = findViewById(R.id.accuracy_value);
        speed_value = findViewById(R.id.speed_value);
        update_value = findViewById(R.id.update_value);
        sensor_value = findViewById(R.id.sensor_value);
        address_value = findViewById(R.id.address_value);
        save_power = findViewById(R.id.save_power);
        location_updates = findViewById(R.id.location_updates);
        way_points_value = findViewById(R.id.way_points_value);

        show_way_points_list = findViewById(R.id.show_way_points_list);
        new_point_list = findViewById(R.id.new_way_points);
        show_map = findViewById(R.id.show_map);
    }

    private void setLocationRequestValues() {
        lr.setInterval(1000 * DEFAULT_UPDATE_INTERVAL);
        lr.setFastestInterval(1000 * Fast_UPDATE_INTERVAL);
        lr.setPriority(lr.PRIORITY_BALANCED_POWER_ACCURACY);
    }

    private void setListeners() {
        save_power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (save_power.isChecked()) {
                    lr.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    sensor_value.setText("High accuracy with GPS");
                } else {
                    lr.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
                    sensor_value.setText("Using towers and WIFI");
                }
            }
        });
        location_updates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (location_updates.isChecked())
                    startFindingLocation();
                else
                    finishFindingLocation();
            }
        });
        new_point_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyLocation myLocation = (MyLocation) getApplicationContext();
                locations = myLocation.getLocations();
                locations.add(currentLocation);
            }
        });
        show_way_points_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowLocationsActivity.class);
                startActivity(intent);
            }
        });
        show_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyMap.class);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("MissingPermission")
    private void startFindingLocation() {
        location_updates.setText("Finding the location");
        flpc.requestLocationUpdates(lr, lc, null);
        updateGPS();
    }

    private void finishFindingLocation() {
        lat_value.setText("Not tracking anymore");
        lon_value.setText("Not tracking anymore");
        altitude_value.setText("Not tracking anymore");
        accuracy_value.setText("Not tracking anymore");
        speed_value.setText("Not tracking anymore");
        location_updates.setText("Not tracking anymore");
        address_value.setText("Not tracking anymore");

        flpc.removeLocationUpdates(lc);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == PERMISSION_FINE_LOCATION && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            updateGPS();
        }
        else if(requestCode == PERMISSION_FINE_LOCATION){
            Toast.makeText(MainActivity.this, "This app needs permission bro.", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateGPS() {
        flpc = LocationServices.getFusedLocationProviderClient(MainActivity.this);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            flpc.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location != null) {
                        updateUIElements(location);

                        currentLocation = location;
                    }
                    else
                        Toast.makeText(MainActivity.this, "Something went wrong please try again", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_FINE_LOCATION);
            }
        }
    }

    private void updateUIElements(Location location) {
        lat_value.setText(String.valueOf(location.getLatitude()));
        lon_value.setText(String.valueOf(location.getLongitude()));
        accuracy_value.setText(String.valueOf(location.getAccuracy()));

        if(location.hasAltitude())
            altitude_value.setText(String.valueOf(location.getAltitude()));
        else
            altitude_value.setText("Not available in your phone");

        if(location.hasSpeed())
            speed_value.setText(String.valueOf(location.getSpeed()));
        else
            speed_value.setText("Not available in your phone");

        try {
            Geocoder g = new Geocoder(this);
            List<Address> addresses = g.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            address_value.setText(addresses.get(0).getAddressLine(0));
        } catch (IOException e) {
            address_value.setText("Could not get the address");
            e.printStackTrace();
        }

        way_points_value.setText(String.valueOf(locations.size()));
    }
}