package com.example.sqlite_second_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText name, age;
    private Switch is_active;
    private Button view_all, add;
    private DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeElements();
        db = new DB(this);
        setListeners();
    }

    private void setListeners() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().length() > 0 && name.getText().toString().length() <= 20 && Integer.parseInt(age.getText().toString()) > 4 && Integer.parseInt(age.getText().toString()) <= 150)
                    if(db.insertCustomer(name.getText().toString(), Integer.parseInt(age.getText().toString()), is_active.isChecked()))
                        Toast.makeText(MainActivity.this, "Customer added successfully", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this, "something went wrong please try again", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "name and age must be between 1, 20 characters and 5, 150", Toast.LENGTH_SHORT).show();
            }
        });

        view_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setCancelable(true);
                builder.setTitle("Matches");
                builder.setMessage(db.getAll());
                builder.show();
            }
        });
    }

    private void initializeElements() {
        name = findViewById(R.id.customer_name);
        age = findViewById(R.id.customer_age);
        is_active = findViewById(R.id.is_active);
        view_all = findViewById(R.id.view_all);
        add = findViewById(R.id.add);
    }
}