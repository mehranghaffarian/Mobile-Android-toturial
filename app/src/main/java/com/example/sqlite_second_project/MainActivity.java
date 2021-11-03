package com.example.sqlite_second_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    private EditText name, id;
    private Switch is_active;
    private Button view_all, add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeElements();

        setListeners();
    }

    private void setListeners() {

    }

    private void initializeElements() {
        name = findViewById(R.id.customer_name);
        id = findViewById(R.id.customer_id);
        is_active = findViewById(R.id.is_active);
        view_all = findViewById(R.id.view_all);
        add = findViewById(R.id.add);
    }
}