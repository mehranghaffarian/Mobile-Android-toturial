package com.example.libraryapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

        overridePendingTransition(R.anim.moving_in, R.anim.moving_out);

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
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("About");
                builder.setMessage("Do you want to visit our website?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(MainActivity.this, WebsiteActivity.class));
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.create().show();
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

    @Override
    public void finish(){
        super.finish();

        overridePendingTransition(R.anim.moving_out, R.anim.moving_in);
    }
}