package com.example.hello;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private CheckBox j, r, p;
    private RadioGroup rdgGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        j = findViewById(R.id.jazz);
        r = findViewById(R.id.rock);
        p = findViewById(R.id.pop);

        if(!p.isChecked())
            Toast.makeText(MainActivity.this, "you have not chosen jazz music?!?!", Toast.LENGTH_SHORT).show();

        j.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    Toast.makeText(MainActivity.this, "you have chosen jazz music", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "you have not chosen jazz music?!?!", Toast.LENGTH_SHORT).show();
            }
        });

        rdgGroup = findViewById(R.id.rdgGroup);
        RadioButton rb = findViewById(R.id.hidden);

        if(rb.isChecked())
            Toast.makeText(MainActivity.this, "you have not chosen your gender", Toast.LENGTH_SHORT).show();

        rdgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.male)
                        Toast.makeText(MainActivity.this, "you have chosen male", Toast.LENGTH_SHORT).show();
                else if(i == R.id.female)
                        Toast.makeText(MainActivity.this, "you have chosen female", Toast.LENGTH_SHORT).show();
            }
        });
    }
}