package com.example.weatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.action_weather:
                        //Toast.makeText(MainActivity.this,"Weather Clicked",Toast.LENGTH_SHORT).show();
                        Intent intentMain = new Intent(SettingsActivity.this,MainActivity.class);
                        startActivity(intentMain);
                        break;

                    case R.id.action_settings:
                        //Toast.makeText(MainActivity.this," Settings Clicked",Toast.LENGTH_SHORT).show();
                        Intent intentSettings = new Intent(SettingsActivity.this,SettingsActivity.class);
                        startActivity(intentSettings);
                        break;
                }
                return true;
            }
        });
    }
}
