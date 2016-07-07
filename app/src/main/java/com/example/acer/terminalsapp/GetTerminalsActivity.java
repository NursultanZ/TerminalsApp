package com.example.acer.terminalsapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class GetTerminalsActivity extends AppCompatActivity {
    TerminalDataBase db;
    TerminalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_terminals);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter = new TerminalAdapter(GetTerminalsActivity.this, getData(), GetTerminalsActivity.this.getApplicationContext());
        ListView lvNotes = (ListView) findViewById(R.id.listView);
        lvNotes.setAdapter(adapter);
        getSupportActionBar().setTitle("Терминалы (" + String.valueOf(adapter.getCount()) + ")");
    }
    public ArrayList<Point> getData() {
        db = new TerminalDataBase(GetTerminalsActivity.this.getApplicationContext());
        final ArrayList<Point> stringItems = new ArrayList<>();
        ArrayList<Point> pr = (ArrayList<Point>) db.getAllPoints();
        for (Point p : pr){
            stringItems.add(p);
        }
        return stringItems;
    }
}
