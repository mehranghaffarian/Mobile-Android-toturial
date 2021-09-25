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

        Database.getDatabase();//for initializing the arraylists

        initializeUI();

        setListeners();
    }

    private void setListeners() {
        allBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database.getDatabase().setCategory(Database.Category.ALL);

                startActivity(new Intent(MainActivity.this, BookCategory.class));
            }
        });
        haveReadBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database.getDatabase().setCategory(Database.Category.HAVE_READ);

                startActivity(new Intent(MainActivity.this, BookCategory.class));
            }
        });
        favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database.getDatabase().setCategory(Database.Category.FAVORITES);

                startActivity(new Intent(MainActivity.this, BookCategory.class));
            }
        });
        currentBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database.getDatabase().setCategory(Database.Category.CURRENTLY_READING);

                startActivity(new Intent(MainActivity.this, BookCategory.class));
            }
        });
        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database.getDatabase().setCategory(Database.Category.WISHLIST);

                startActivity(new Intent(MainActivity.this, BookCategory.class));
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database.getDatabase().setCategory(Database.Category.ABOUT);

                //TODO: creating the about activity
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