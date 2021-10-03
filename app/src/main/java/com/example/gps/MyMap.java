package com.example.gps;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.app.Application;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.gps.databinding.ActivityMyMapBinding;

import java.util.ArrayList;

public class MyMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMyMapBinding binding;

    private ArrayList<Location> locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMyMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locations = ((MyLocation) getApplicationContext()).getLocations();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng lastLocation;

        if (locations.size() == 0)
            lastLocation = new LatLng(-34, 151);
        else
            lastLocation = new LatLng(locations.get(locations.size() - 1).getLatitude(), locations.get(locations.size() - 1).getLongitude());

        for (Location l : locations) {
            if (l != null) {
                LatLng temp = new LatLng(l.getLatitude(), l.getLongitude());
                mMap.addMarker(new MarkerOptions().position(temp).title("Lat: " + l.getLatitude() + "Lon: " + l.getLongitude()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(temp));
            }
        }

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lastLocation, 12.0f));

        System.out.println("************" + locations.size());
    }
}