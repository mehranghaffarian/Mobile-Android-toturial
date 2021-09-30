package com.example.gps;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

public class ShowLocationsActivity extends AppCompatActivity {
    private ListView locationsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_show_locations);

        locationsList = findViewById(R.id.locationsList);

        MyLocation myLocation = (MyLocation)getApplicationContext();
        ArrayList<Location> locations = myLocation.getLocations();

        locationsList.setAdapter(new ArrayAdapter<Location>(this, android.R.layout.simple_list_item_1, locations));
    }
}