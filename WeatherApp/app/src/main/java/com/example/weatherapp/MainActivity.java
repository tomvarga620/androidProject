package com.example.weatherapp;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient client;
    Coordinates coord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.darktheme);
        } else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();
        client = LocationServices.getFusedLocationProviderClient(this);

        getLocation();

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
//tu je request
    public void jsonReq(double coordinate1,double coordinate2) {
        String latitude = Double.toString(coordinate1);
        String longitute = Double.toString(coordinate2);
        String apiurl = "http://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitute+"&mode=HTML&appid=91d6e79307bb2126fb26e2d4f5e21bf7";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, apiurl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Log.i("Result request", response.toString());
                jsonParse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error", error.getLocalizedMessage());
            }
        });
        Volley.newRequestQueue(getBaseContext()).add(request);
    }

    public void jsonParse(JSONObject response){
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
        setWeatherIcon(weatherDescription);
        setWeather(nameCity,country,temp,weatherDescription);
    }

    public void setWeather(String city,String country,int temp,String mainDescription){
        float tempCelsius = temp-273.15F;
        String formattedTemp = String.format("%.02f",tempCelsius);
        formattedTemp = formattedTemp+"°C";
        TextView textLocation = findViewById(R.id.text_view_location);
        TextView textTemp = findViewById(R.id.text_view_temp);
        TextView textStatus = findViewById(R.id.text_view_status);

        textLocation.setText(city);
        textTemp.setText(String.valueOf(formattedTemp));
        textStatus.setText(mainDescription);
    }

    private void getLocation(){

        if(ActivityCompat.checkSelfPermission(MainActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return;
        }

        client.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    coord = new Coordinates(latitude,longitude);
                    jsonReq(latitude,longitude);
                }
            }
        });
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},1);
    }

    private void setWeatherIcon(String weather){
        ImageView icon = findViewById(R.id.weatherIcon);

        switch(weather){
            case "Clouds":
                icon.setBackgroundResource(R.drawable.ic_clouds);
                break;
            case "Clear":
                icon.setBackgroundResource(R.drawable.ic_clear);
                break;
            case "Snow":
                icon.setBackgroundResource(R.drawable.ic_snow);
                break;
            case "Rain":
                icon.setBackgroundResource(R.drawable.ic_rain);
                break;
            case "Mist":
                icon.setBackgroundResource(R.drawable.ic_mist);
                break;
        }

    }
}
