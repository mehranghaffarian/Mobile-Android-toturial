package com.example.hello;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.floating_add);
        rl = findViewById(R.id.mainn_layout);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(rl, "dummmy snackbar", Snackbar.LENGTH_INDEFINITE)
                        .setAction("dummier Toast", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this, "dummier toast created", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setTextColor(getResources().getColor(android.R.color.holo_blue_bright))
                        .setBackgroundTint(getResources().getColor(R.color.medium_dark))
                        .setActionTextColor(getResources().getColor(android.R.color.holo_green_dark))
                        .show();
            }
        });
    }
}