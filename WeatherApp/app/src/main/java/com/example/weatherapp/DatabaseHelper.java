package com.example.weatherapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Items.db";
    private static final String DB_TABLE = "Items_Table";

    private static final String ID = "ID";
    private static final String NAME ="NAME";

    private static final String CREATE_TABLE = "CREATE TABLE "+DB_TABLE+"("+
            ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "+
            NAME +" TEXT " + ")";

    public DatabaseHelper(Context context){
        super(context,DB_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);

        onCreate(db);
    }
}
