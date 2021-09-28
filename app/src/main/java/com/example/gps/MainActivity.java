package com.example.gps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_FINE_LOCATION = 50;
    private static final int DEFAULT_UPDATE_INTERVAL = 30;
    private static final int Fast_UPDATE_INTERVAL = 5;

    private TextView lat_value, lon_value, altitude_value, accuracy_value, speed_value, update_value, sensor_value, address_value;
    private SwitchCompat save_power, location_updates;

    private LocationRequest lr;
    private FusedLocationProviderClient flpc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeElements();

        lr = new LocationRequest();
        setLocationRequestValues();

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
    }

    private void setLocationRequestValues() {
        lr.setInterval(1000 * DEFAULT_UPDATE_INTERVAL);
        lr.setFastestInterval(1000 * Fast_UPDATE_INTERVAL);
        lr.setPriority(lr.PRIORITY_BALANCED_POWER_ACCURACY);
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
                public void onSuccess(@NonNull Location location) {
                    updateUIElements(location);
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
    }

    private void setListeners() {
        save_power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(save_power.isChecked()){
                    lr.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    sensor_value.setText("High accuracy with GPS");
                }
                else {
                    lr.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
                    sensor_value.setText("Using towers and WIFI");
                }
            }
        });
    }
}