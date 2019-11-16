package com.example.weatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_weather:
                        //Toast.makeText(MainActivity.this,"Weather Clicked",Toast.LENGTH_SHORT).show();
                        Intent intentMain = new Intent(AddActivity.this, MainActivity.class);
                        startActivity(intentMain);
                        break;

                    case R.id.action_add:
                        //Toast.makeText(MainActivity.this," Settings Clicked",Toast.).show();
                        Intent intentAdd = new Intent(AddActivity.this, AddActivity.class);
                        startActivity(intentAdd);
                        break;

                    case R.id.action_settings:
                        //Toast.makeText(MainActivity.this," Settings Clicked",Toast.).show();
                        Intent intentSettings = new Intent(AddActivity.this, SettingsActivity.class);
                        startActivity(intentSettings);
                        break;
                }
                return true;
            }
        });
    }
}
