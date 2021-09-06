package com.example.hello;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == R.id.help) {
            Toast.makeText(MainActivity.this, "heeeeelp", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(item.getItemId() == R.id.about) {
            Toast.makeText(MainActivity.this, "abooooooooout", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(item.getItemId() == R.id.settings) {
            Toast.makeText(MainActivity.this, "settttttttings", Toast.LENGTH_SHORT).show();
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }
}