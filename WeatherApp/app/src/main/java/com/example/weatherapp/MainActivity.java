package com.example.weatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static androidx.appcompat.app.AppCompatDelegate.getDefaultNightMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.darktheme);
        } else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jsonParse();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_weather:
                        //Toast.makeText(MainActivity.this,"Weather Clicked",Toast.LENGTH_SHORT).show();
                        Intent intentMain = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intentMain);
                        break;

                    case R.id.action_settings:
                        //Toast.makeText(MainActivity.this," Settings Clicked",Toast.).show();
                        Intent intentSettings = new Intent(MainActivity.this, SettingsActivity.class);
                        startActivity(intentSettings);
                        break;
                }
                return true;
            }
        });
    }

    public void jsonParse() {
        String apiurl = "http://api.openweathermap.org/data/2.5/weather?q=Ko%C5%A1ice,sk&mode=HTML&appid=91d6e79307bb2126fb26e2d4f5e21bf7";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, apiurl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String nameCity="";
                String weatherDescription="";
                String country ="";
                int temp=0;
                try {
                    nameCity = response.getString("name");
                    JSONArray weather = response.getJSONArray("weather");
                    JSONObject main = response.getJSONObject("main");
                    JSONObject sys = response.getJSONObject("sys");
                    weatherDescription = weather.getJSONObject(0).getString("main");
                    temp = main.getInt("temp");
                    country = sys.getString("country");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setWeather(nameCity,country,temp,weatherDescription);
                //Log.i("Result request", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error", error.getLocalizedMessage());
            }
        });
        Volley.newRequestQueue(getBaseContext()).add(request);
    }

    public void setWeather(String city,String country,int temp,String mainDescription){
        float tempCelsius = temp-273.15F;
        String formattedTemp = String.format("%.02f",tempCelsius);
        formattedTemp = formattedTemp+"°";
        TextView textLocation = findViewById(R.id.text_view_location);
        TextView textTemp = findViewById(R.id.text_view_temp);
        TextView textStatus = findViewById(R.id.text_view_status);

        textLocation.setText(city);
        textTemp.setText(String.valueOf(formattedTemp));
        textStatus.setText(mainDescription);
    }
}
