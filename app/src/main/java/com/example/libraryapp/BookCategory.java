package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;

public class BookCategory extends AppCompatActivity {
    private TextView category;
    private TextView noBookTxt;

    private RecyclerView allBooksRecView;
    private BookViewAdap bookViewAdap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_category_books);

        category = findViewById(R.id.category);
        noBookTxt = findViewById(R.id.noBook);

        allBooksRecView = findViewById(R.id.allBooksRecView);
        bookViewAdap = new BookViewAdap(this, noBookTxt);

        allBooksRecView.setAdapter(bookViewAdap);
        allBooksRecView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Book> targetBooks = Database.getDatabase().getBooks();
        bookViewAdap.setBooks(targetBooks);

        if(targetBooks == null || targetBooks.size() == 0)
            noBookTxt.setVisibility(View.VISIBLE);

        category.setText(Database.getDatabase().getCategoryName());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BookCategory.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        Database.getDatabase().setCategory(Database.Category.NONE);
    }
}