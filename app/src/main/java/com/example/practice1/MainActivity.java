package com.example.practice1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText userPass;
    private EditText userPassConfirm;
    private CheckBox showPass;
    private Spinner countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userPass = findViewById(R.id.userPass);
        userPassConfirm = findViewById(R.id.userConfirmPass);
        showPass = findViewById(R.id.showPass);
        countries = findViewById(R.id.countries);

        ArrayList<String> countriesList = new ArrayList<>();
        countriesList.add("CHOOSE");
        countriesList.add("IRI");
        countriesList.add("USA");
        countriesList.add("UK");

        ArrayAdapter<String> countriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countriesList);

        countries.setAdapter(countriesAdapter);

        showPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    userPass.setInputType(InputType.TYPE_CLASS_TEXT);
                    userPassConfirm.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                else {
                    userPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    userPassConfirm.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                userPass.setSelection(userPass.getText().length());
                userPassConfirm.setSelection(userPassConfirm.getText().length());
            }
        });
    }
}