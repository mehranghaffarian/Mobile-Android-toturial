package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.bumptech.glide.Glide;

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

        Book b = null;

        initializeViews();

        Intent intent = getIntent();

        if (intent != null) {
            int bookId = intent.getIntExtra("index", -1);

            if (bookId != -1) {
                b = Database.getDatabase().findBook(bookId);

                if (b != null)
                    importData(b);
            }
        } else
            Toast.makeText(SingleBook.this, "Something went wrong try again", Toast.LENGTH_SHORT).show();

        handleAddToHaveRead(b);
        handleAddToWishlist(b);
        handleAddToFavorites(b);
        handleAddToReading(b);
    }

    private void handleAddToHaveRead(Book b) {
        if (Database.getDatabase().hasRead(b)) {
            haveRead.setVisibility(View.GONE);
        } else
            haveRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Database.getDatabase().addToHaveRead(b)) {
                        Toast.makeText(SingleBook.this, "Added successfully", Toast.LENGTH_SHORT).show();

                        haveRead.setVisibility(View.GONE);
                        Database.getDatabase().setCategory(Database.Category.HAVE_READ);

                        startActivity(new Intent(SingleBook.this, BookCategory.class));
                    } else
                        Toast.makeText(SingleBook.this, "Something went wrong try again", Toast.LENGTH_SHORT).show();
                }
            });
    }

    private void handleAddToWishlist(Book b) {
        if (Database.getDatabase().inWishlist(b)) {
            wishlist.setVisibility(View.GONE);
        } else
            wishlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Database.getDatabase().addToWishlist(b)) {
                        Toast.makeText(SingleBook.this, "Added successfully", Toast.LENGTH_SHORT).show();

                        wishlist.setVisibility(View.GONE);
                        Database.getDatabase().setCategory(Database.Category.WISHLIST);

                        startActivity(new Intent(SingleBook.this, BookCategory.class));
                    } else
                        Toast.makeText(SingleBook.this, "Something went wrong try again", Toast.LENGTH_SHORT).show();
                }
            });
    }

    private void handleAddToFavorites(Book b) {
        if (Database.getDatabase().isFavorite(b)) {
            favorites.setVisibility(View.GONE);
        } else
            favorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Database.getDatabase().addToFavorites(b)) {
                        Toast.makeText(SingleBook.this, "Added successfully", Toast.LENGTH_SHORT).show();

                        favorites.setVisibility(View.GONE);
                        Database.getDatabase().setCategory(Database.Category.FAVORITES);

                        startActivity(new Intent(SingleBook.this, BookCategory.class));
                    } else
                        Toast.makeText(SingleBook.this, "Something went wrong try again", Toast.LENGTH_SHORT).show();
                }
            });
    }

    private void handleAddToReading(Book b) {
        if (Database.getDatabase().isReading(b)) {
            currentReading.setVisibility(View.GONE);
        } else
            currentReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Database.getDatabase().addToCurrentlyReading(b)) {
                        Toast.makeText(SingleBook.this, "Added successfully", Toast.LENGTH_SHORT).show();

                        currentReading.setVisibility(View.GONE);
                        Database.getDatabase().setCategory(Database.Category.CURRENTLY_READING);

                        startActivity(new Intent(SingleBook.this, BookCategory.class));
                    } else
                        Toast.makeText(SingleBook.this, "Something went wrong try again", Toast.LENGTH_SHORT).show();
                }
            });
    }

    private void importData(Book b) {
        bookTitle.setText(b.getTitle());
        authorName.setText(b.getAuthor());
        shortDec.setText(b.getShortDec());
        pages.setText(String.valueOf(b.getPages()));
        longDec.setText(b.getLongDec());

//        Glide.with(this).asBitmap().load(b.getImageURL()).into(bookImage);
    }

    private void initializeViews() {
        bookTitle = findViewById(R.id.bookTitle);
        authorName = findViewById(R.id.authorName2);
        pages = findViewById(R.id.pages);
        shortDec = findViewById(R.id.shortDec2);
        longDec = findViewById(R.id.longDec2);

        bookImage = findViewById(R.id.bookImage2);

        currentReading = findViewById(R.id.addToReadingBooks);
        favorites = findViewById(R.id.addToFavorites);
        wishlist = findViewById(R.id.addToWishlist);
        haveRead = findViewById(R.id.addToHaveRead);
    }
}