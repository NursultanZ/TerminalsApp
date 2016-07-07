package com.example.acer.terminalsapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class ShowPointActivity extends AppCompatActivity implements OnMapReadyCallback {

    String name;
    Double latitude;
    Double longitude;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_point);
        getSupportActionBar().isShowing();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        name = getIntent().getStringExtra("text_note");
        latitude = Double.valueOf(getIntent().getStringExtra("lat"));
        longitude = Double.valueOf(getIntent().getStringExtra("longt"));
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        TerminalDataBase db = new TerminalDataBase(ShowPointActivity.this);
        LocationDataBase dbb = new LocationDataBase(ShowPointActivity.this);
            LatLng lat = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin))
                    .position(lat)
                    .title(name));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 13));
        List<Location> nl = dbb.getLocation();
        int i = 0;
        Double latt = Double.valueOf(nl.get(i).getLat());
        Double longtt = Double.valueOf(nl.get(i).getLongt());
        LatLng loc = new LatLng(latt, longtt);
        Log.d("Loc", String.valueOf(loc));
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.userloc))
                .position(loc)
                .title("Ваше месторасположение"));
    }
}


