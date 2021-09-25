package com.example.libraryapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

//import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookViewAdap extends RecyclerView.Adapter<BookViewAdap.ViewHolder> {
    private final String TAG = "BookViewAdap";

    private ArrayList<Book> books;
    private Context c;
    private TextView noBookTxt;

    public BookViewAdap(Context c, TextView noBookTxt) {
        this.c = c;
        this.noBookTxt = noBookTxt;
        books = new ArrayList<Book>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.book_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {
        Log.d(TAG, "onBindViewHolder: started");

        viewHolder.bookName.setText(books.get(i).getTitle());
        viewHolder.authorName.setText(books.get(i).getAuthor());
        viewHolder.shortDec.setText(books.get(i).getShortDec());

//        Glide.with(c).asBitmap().load(books.get(i).getImageURL()).into(viewHolder.bookImage);

        viewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, SingleBook.class);
                intent.putExtra("index", books.get(i).getId());

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

        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                builder.setMessage("Do you want to delete " + books.get(i).getTitle() + " ?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int alertIndex) {
                        if (Database.getDatabase().deleteBook(books.get(i))) {
                            Toast.makeText(c, "Removed successfully", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();

                            viewHolder.hiddenDec.setVisibility(View.GONE);
                            viewHolder.downArrow.setVisibility(View.VISIBLE);
                        } else
                            Toast.makeText(c, "Something happened wrong please try again", Toast.LENGTH_SHORT).show();

                        if (books.size() == 0)
                            noBookTxt.setVisibility(View.VISIBLE);
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int alertIndex) {
                    }
                });
                builder.create().show();
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView parent;
        private ImageView bookImage;
        private TextView bookName;

        private ImageView upArrow;
        private ImageView downArrow;
        private ImageView delete;

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
            delete = itemView.findViewById(R.id.delete);

            hiddenDec = itemView.findViewById(R.id.hiddenRelLayout);
            authorName = itemView.findViewById(R.id.authorName);
            shortDec = itemView.findViewById(R.id.shortDec);
        }
    }
}
