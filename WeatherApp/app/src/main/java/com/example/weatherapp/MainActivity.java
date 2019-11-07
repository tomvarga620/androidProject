package com.example.weatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import static androidx.appcompat.app.AppCompatDelegate.getDefaultNightMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(getDefaultNightMode());
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.action_weather:
                        //Toast.makeText(MainActivity.this,"Weather Clicked",Toast.LENGTH_SHORT).show();
                        Intent intentMain = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(intentMain);
                        break;

                    case R.id.action_settings:
                        //Toast.makeText(MainActivity.this," Settings Clicked",Toast.).show();
                        Intent intentSettings = new Intent(MainActivity.this,SettingsActivity.class);
                        startActivity(intentSettings);
                        break;
                }
                return true;
            }
        });
    }
}
