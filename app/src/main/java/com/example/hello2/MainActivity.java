package com.example.hello2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView relView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relView = findViewById(R.id.contactsRelView);

        ArrayList<Contact> contacts = new ArrayList<>();

        contacts.add(new Contact("m1", "m1@gmail.com", "091", "https://images.saymedia-content.com/.image/ar_1:1%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cq_auto:eco%2Cw_1200/MTc0NDI5MTc5NzI1NDg5Nzk4/top-10-greatest-leonardo-dicaprio-movies.jpg"));
        contacts.add(new Contact("m2", "m2@gmail.com", "092", "https://m.media-amazon.com/images/M/MV5BMjI0MTg3MzI0M15BMl5BanBnXkFtZTcwMzQyODU2Mw@@._V1_UY1200_CR130,0,630,1200_AL_.jpg"));
        contacts.add(new Contact("m3", "m3@gmail.com", "093", "https://upload.wikimedia.org/wikipedia/commons/2/25/Leonardo_DiCaprio_2014.jpg"));
        contacts.add(new Contact("m4", "m4@gmail.com", "094", "https://d1jyxxz9imt9yb.cloudfront.net/person/1571/detail_image/mobile/LDC-High-Res-Headshot.jpg"));

        ContactsRelView crv = new ContactsRelView(this);
        crv.setContacts(contacts);

        relView.setAdapter(crv);
        relView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}