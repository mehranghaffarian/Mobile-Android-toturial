package com.example.hello2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ContactsRelView extends RecyclerView.Adapter<ContactsRelView.ViewHolder> {
    private ArrayList<Contact> contacts;
    private Context context;

    public ContactsRelView(Context c) {
        contacts = new ArrayList<>();
        context = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cotancts_layout, viewGroup, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.contactName.setText(contacts.get(i).toString());
        viewHolder.contactEmail.setText(contacts.get(i).getEmail());

        viewHolder.contactView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, contacts.get(viewHolder.getAdapterPosition()) + " selected", Toast.LENGTH_SHORT).show();
            }
        });
        Glide.with(context)
                .asBitmap()
                .load(contacts.get(i).getImageUrl())
                .into(viewHolder.contactImage);
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView contactName;
        private TextView contactEmail;
        private ImageView contactImage;
        private CardView contactView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contactName = itemView.findViewById(R.id.contactName);
            contactEmail = itemView.findViewById(R.id.contactEmail);
            contactView = itemView.findViewById(R.id.contactView);
            contactImage = itemView.findViewById(R.id.contactImage);
        }
    }
}
