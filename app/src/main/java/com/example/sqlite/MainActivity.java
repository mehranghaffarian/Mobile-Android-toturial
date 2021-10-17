package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView name, phone_number, email;
    private Button register, update, delete, show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeUIElements();
    }

    private void initializeUIElements() {
        name = findViewById(R.id.name);
        phone_number = findViewById(R.id.phone_number);
        email = findViewById(R.id.email);
        register = findViewById(R.id.register_user);
        update = findViewById(R.id.update_user);
        delete = findViewById(R.id.delete_user);
        show = findViewById(R.id.show_users);
    }
}