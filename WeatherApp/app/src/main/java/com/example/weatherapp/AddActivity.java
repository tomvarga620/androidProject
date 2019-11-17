package com.example.weatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

    DatabaseHelper db;
    Button btn_add_data;
    EditText add_item;

    ListView list;

    ArrayList<String> listItem;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        db = new DatabaseHelper(this);

        listItem = new ArrayList<>();

        btn_add_data = findViewById(R.id.add_data);
        add_item = findViewById(R.id.add_item);
        list = findViewById(R.id.itemsList);


        viewData();

        btn_add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = add_item.getText().toString();
                if(!itemName.equals("") && db.insertData(itemName)){
                    Toast.makeText(AddActivity.this,"Data added",Toast.LENGTH_SHORT);
                    add_item.setText("");
                } else {
                    Toast.makeText(AddActivity.this,"Data not added",Toast.LENGTH_SHORT);
                }
            }
        });

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

    private void viewData() {
        Cursor cursor = db.viewData();

        if(cursor.getCount() == 0){
            Toast.makeText(AddActivity.this,"No data",Toast.LENGTH_SHORT);
        } else {
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(1));
            }

            adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listItem);
            list.setAdapter(adapter);
        }
    }
}
