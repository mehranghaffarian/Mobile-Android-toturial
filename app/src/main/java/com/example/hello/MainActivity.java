package com.example.hello;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edt;

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn:
                Toast.makeText(this, "is working right? " + edt.getText().toString(), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt = findViewById(R.id.edtName);
    }
}