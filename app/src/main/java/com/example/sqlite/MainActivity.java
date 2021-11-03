package com.example.sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText name, phone_number, email;
    private Button register, update, delete, show;

    private DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DB(this);

        initializeUIElements();

        setListeners();
    }

    private void setListeners() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = name.getText().toString();
                String phone = phone_number.getText().toString();
                String email_text = email.getText().toString();

                if(username.length() > 3) {
                    if(phone.length() == 11) {
                        if(email_text.contains(".com") && email_text.contains("@")) {
                            if(db.registerUser(username, phone, email_text)) {
                                Toast.makeText(MainActivity.this, "User registered successfully.", Toast.LENGTH_SHORT).show();

                                clearFields();
                            }
                            else
                                Toast.makeText(MainActivity.this, "something went wrong, user already exists or try again", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            email.setText("Please enter valid email");
                            email.setTextColor(Color.rgb(255, 0, 0));
                        }
                    }
                    else {
                        phone_number.setText("phone number must be 11 numbers");
                        phone_number.setTextColor(Color.rgb(255, 0, 0));
                    }
                }
                else{
                    name.setText("username must be at least 4 characters");
                    name.setTextColor(Color.rgb(255, 0, 0));
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(db.updateUser(name.getText().toString(), phone_number.getText().toString(), email.getText().toString())) {
                    Toast.makeText(MainActivity.this, "user updated successfully", Toast.LENGTH_SHORT).show();

                    clearFields();
                }
                else
                    Toast.makeText(MainActivity.this, "something went wrong please check your information", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(db.deleteUser(name.getText().toString())) {
                    Toast.makeText(MainActivity.this, "user deleted successfully", Toast.LENGTH_SHORT).show();

                    clearFields();
                }
                else
                    Toast.makeText(MainActivity.this, "something went wrong please check your information", Toast.LENGTH_SHORT).show();
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.getData();

                if(res.getCount() == 0)
                    Toast.makeText(MainActivity.this, "No matches found?!?!", Toast.LENGTH_SHORT).show();
                else{
                    StringBuffer buffer = new StringBuffer();

                    while (res.moveToNext()){
                        buffer.append("Username: " + res.getString(0));
                        buffer.append("\nPhone number: " + res.getString(1));
                        buffer.append("\nEmail: " + res.getString(2) + "\n\n");
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    builder.setCancelable(true);
                    builder.setTitle("Users matches");
                    builder.setMessage(buffer);
                    builder.show();
                }
            }
        });
    }

    private void clearFields(){
        name.setText("");
        name.setTextColor(Color.rgb(0, 0, 0));
        phone_number.setText("");
        phone_number.setTextColor(Color.rgb(0, 0, 0));
        email.setText("");
        email.setTextColor(Color.rgb(0, 0, 0));
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