package com.example.acer.terminalsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 20.06.2016.
 */
public class LocationDataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "locationsApp";
    final String LOG_TAG = "LocationsDataBase";
    private static final int DATABASE_VERSION = 2;

    public LocationDataBase(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(LOG_TAG, "Вызов конструктора");
        Log.d(LOG_TAG, "Создание Базы Данных");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, "Функция onCreate создание таблиц БД");
        String sQuery = "CREATE TABLE Locations (id integer primary key autoincrement, lat text, longt text);";
        db.execSQL(sQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void addLocation(Location location) {
        SQLiteDatabase db = this.getWritableDatabase();
        dropTable();
        ContentValues cv = new ContentValues();
        cv.put("lat", location.getLat());
        cv.put("longt", location.getLongt());
        db.insert("Locations", null, cv);
        Log.d("Add location", location.toString());
        db.close();
    }


    public List<Location> getLocation() {
        List<Location> pointList = new ArrayList<Location>();
        String selectQuery = "SELECT * FROM Locations";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Location location = new Location();
                location.setId(Integer.valueOf(cursor.getString(0)));
                location.setLat(String.valueOf(Double.valueOf(cursor.getString(1))));
                location.setLongt(String.valueOf(Double.valueOf(cursor.getString(2))));
                pointList.add(location);
            } while (cursor.moveToNext());
        }
        db.close();
        return pointList;
    }
    public void dropTable(){
        SQLiteDatabase db = this.getReadableDatabase();
        String s = "DROP TABLE Locations;";
        db.execSQL(s);
        onCreate(db);
    }
}

