package com.example.libraryapp;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

//import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookViewAdap extends RecyclerView.Adapter<BookViewAdap.ViewHolder>{
    private final String TAG = "BookViewAdap";

    private ArrayList<Book> books;
    private Context c;

    public BookViewAdap(Context c) {
        this.c = c;
        books = new ArrayList<Book>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.book_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: started");

        viewHolder.bookName.setText(books.get(i).getTitle());
        viewHolder.authorName.setText(books.get(i).getAuthor());
        viewHolder.shortDec.setText(books.get(i).getShortDec());

//        Glide.with(c).asBitmap().load(books.get(i).getImageURL()).into(viewHolder.bookImage);

        viewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, SingleBook.class);
                intent.putExtra("index", i);

                c.startActivity(intent);
            }
        });

        viewHolder.downArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(viewHolder.parent);
                viewHolder.hiddenDec.setVisibility(View.VISIBLE);
                viewHolder.downArrow.setVisibility(View.GONE);
            }
        });

        viewHolder.upArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(viewHolder.parent);
                viewHolder.hiddenDec.setVisibility(View.GONE);
                viewHolder.downArrow.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private ImageView bookImage;
        private TextView bookName;

        private ImageView upArrow;
        private ImageView downArrow;

        private RelativeLayout hiddenDec;
        private TextView authorName;
        private TextView shortDec;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            bookImage = itemView.findViewById(R.id.bookImage);
            bookName = itemView.findViewById(R.id.bookName);

            upArrow = itemView.findViewById(R.id.upArrow);
            downArrow = itemView.findViewById(R.id.downArrow);

            hiddenDec = itemView.findViewById(R.id.hiddenRelLayout);
            authorName = itemView.findViewById(R.id.authorName);
            shortDec = itemView.findViewById(R.id.shortDec);
        }
    }
 }
