package com.example.hello;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.carsList);
        spinner = findViewById(R.id.carsSpinner);

        ArrayList<String> carsArray = new ArrayList<>();
        carsArray.add("BMW");
        carsArray.add("Toyota");
        carsArray.add("Samand");
        carsArray.add("Hunda");
        carsArray.add("Pride");

        ArrayList<String> carsNewArray = new ArrayList<>();
        carsNewArray.add("new BMW");
        carsNewArray.add("new Toyota");
        carsNewArray.add("new Samand");
        carsNewArray.add("new Hunda");
        carsNewArray.add("new Pride");

        ArrayAdapter<String> carsAdp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, carsArray);
        ArrayAdapter<String> carsNewAdp = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, carsNewArray);

        lv.setAdapter(carsAdp);
        spinner.setAdapter(carsAdp);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "you have chosen " + carsArray.get(i), Toast.LENGTH_SHORT).show();
            }
        });
    }
}