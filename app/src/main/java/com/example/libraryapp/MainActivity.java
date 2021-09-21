package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button allBooks, favorites, haveReadBooks, wishlist, currentBooks, about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        initializeUI();

        allBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AllBooks.class));
            }
        });
    }

    private void initializeUI() {
        allBooks = findViewById(R.id.allBooks);
        currentBooks = findViewById(R.id.inRead);
        favorites = findViewById(R.id.favorites);
        wishlist = findViewById(R.id.wishlist);
        haveReadBooks = findViewById(R.id.haveRead);
        about = findViewById(R.id.about);
    }
}