package com.example.weatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;



public class SettingsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    Switch switchDarkMode = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        switchDarkMode = findViewById(R.id.switchDarkMode);
        switchDarkMode.setOnCheckedChangeListener(this);

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

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            SharedPreferences sharedPref = getSharedPreferences("Preference", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("switchDarkMode",true);
            editor.apply();
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            SharedPreferences sharedPref = getSharedPreferences("Preference", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("switchDarkMode",false);
            editor.apply();
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

}
