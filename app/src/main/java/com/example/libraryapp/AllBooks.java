package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;

public class AllBooks extends AppCompatActivity {
    private TextView category;
    private RecyclerView allBooksRecView;
    private BookViewAdap bookViewAdap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_all_books);

        category = findViewById(R.id.category);
        category.setText(Database.getDatabase().getCategoryName());

        allBooksRecView = findViewById(R.id.allBooksRecView);
        bookViewAdap = new BookViewAdap(this);

        bookViewAdap.setBooks(Database.getDatabase().getBooks());

        allBooksRecView.setAdapter(bookViewAdap);
        allBooksRecView.setLayoutManager(new LinearLayoutManager(this));
    }
}