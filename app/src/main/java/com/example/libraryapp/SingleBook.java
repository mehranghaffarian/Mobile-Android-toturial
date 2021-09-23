package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SingleBook extends AppCompatActivity {
    private TextView bookTitle, authorName, pages, shortDec, longDec;
    private ImageView bookImage;
    private Button currentReading, wishlist, haveRead, favorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_single_book);

        initializeViews();

        Book b = ;

        importData(b);
    }

    private void importData(Book b) {
        bookTitle.setText(b.getTitle());
        authorName.setText(b.getAuthor());
        shortDec.setText(b.getShortDec());
        pages.setText(String.valueOf(b.getPages()));
        longDec.setText(b.getLongDec());

        Glide.with(this).asBitmap().load(b.getImageURL()).into(bookImage);
    }

    private void initializeViews() {
        bookTitle = findViewById(R.id.bookTitle);
        authorName = findViewById(R.id.authorName2);
        pages = findViewById(R.id.pages);
        shortDec = findViewById(R.id.shortDec2);
        longDec = findViewById(R.id.longDec2);
        bookImage = findViewById(R.id.bookImage2);
        currentReading = findViewById(R.id.addToReadingBooks);
        currentReading = findViewById(R.id.addToFavorites);
        currentReading = findViewById(R.id.addToWishlist);
        currentReading = findViewById(R.id.addToHaveRead);
    }
}