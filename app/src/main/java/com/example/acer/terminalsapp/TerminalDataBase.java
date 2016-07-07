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
 * Created by Acer on 13.06.2016.
 */
public class TerminalDataBase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "terminalApp";
    final String LOG_TAG = "TerminalDataBase";
    private static final int DATABASE_VERSION = 3;

    public TerminalDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(LOG_TAG, "Вызов конструктора");
        Log.d(LOG_TAG, "Создание Базы Данных");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, "Функция onCreate создание таблиц БД");
        String sQuery = "CREATE TABLE POINTS (id integer primary key autoincrement, point_name text, lat text, longt text);";
        db.execSQL(sQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void addPoint(Point point) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("point_name", point.getPoint_name());
        cv.put("lat", point.getLat());
        cv.put("longt", point.getLongt());
        db.insert("POINTS", null, cv);
        Log.d("Add point", point.toString());
        db.close();
    }

    public List<Point> getAllPoints() {
        List<Point> pointList = new ArrayList<Point>();
        String selectQuery = "SELECT * FROM POINTS";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Point point = new Point();
                point.setId(Integer.valueOf(cursor.getString(0)));
                point.setPoint_name(cursor.getString(1));
                point.setLat(String.valueOf(Double.valueOf(cursor.getString(2))));
                point.setLongt(String.valueOf(Double.valueOf(cursor.getString(3))));
                pointList.add(point);
            } while (cursor.moveToNext());
        }
        db.close();
        return pointList;
    }
    public void dropTablePOINTS(){
        SQLiteDatabase db = this.getReadableDatabase();
        String s = "DROP TABLE POINTS;";
        db.execSQL(s);
        onCreate(db);
    }
}
