package com.example.acer.terminalsapp;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Map;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        getSupportActionBar().isShowing();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_maps, menu);
        return true;
    }
    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.call){
            Intent i = new Intent(this, CallDeveloperActivity.class);
            startActivity(i);
        }
        if (id == R.id.db){
            Intent i = new Intent(this, GetTerminalsActivity.class);
            startActivity(i);
        }
        if (id == R.id.loc){
            GPSTracker gp = new GPSTracker(MapsActivity.this);
            if (gp.canGetLocation()) {
                LocationDataBase dbb = new LocationDataBase(MapsActivity.this);
                double lat = 0.0;
                double longT = 0.0;
                lat = gp.getLatitude();
                longT = gp.getLongitude();
                String latt = String.valueOf(lat);
                String longt = String.valueOf(longT);
                Location location = new Location(latt, longt);
                dbb.addLocation(location);
                List<Location> nl = dbb.getLocation();
                int i = 0;
                Double latitude = Double.valueOf(nl.get(i).getLat());
                Double longtitude = Double.valueOf(nl.get(i).getLongt());
                LatLng loc = new LatLng(latitude, longtitude);
                Log.d("Loc", String.valueOf(loc));
                Marker name = mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.userloc))
                        .position(loc)
                        .title("Ваше месторасположение"));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longtitude), 17));
            }else {
                Toast t = Toast.makeText(this, "Включите настройки геолокации", Toast.LENGTH_SHORT);
                t.show();
                gp.showSettingsAlert();
            }
        }
        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
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
        TerminalDataBase db = new TerminalDataBase(MapsActivity.this);
        List<Point> nl = db.getAllPoints();
        for (int i = 0; i < nl.size(); i++) {
            int id = nl.get(i).getId();
            String name = nl.get(i).getPoint_name();
            Double latitude = Double.valueOf(nl.get(i).getLat());
            Double longitude = Double.valueOf(nl.get(i).getLongt());
            LatLng lat = new LatLng(latitude, longitude);
//            mMap.addMarker(new MarkerOptions().position(lat).title(String.valueOf(nl.get(i).getId()) + " " + nl.get(i).getNote_text()));
            if ((id % 3) == 0) {
                mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin))
                        .position(lat)
                        .title(name));
            } else if((id % 2) == 0){
                mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin))
                        .position(lat)
                        .title(name));
            }else {
                mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin))
                        .position(lat)
                        .title(name));
            }
        }
        Double latitude = Double.valueOf(nl.get(0).getLat());
        Double longitude = Double.valueOf(nl.get(0).getLongt());
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 14));
        // Other supported types include: MAP_TYPE_NORMAL,
        // MAP_TYPE_TERRAIN, MAP_TYPE_HYBRID and MAP_TYPE_NONE
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    /*   mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.mip))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(new LatLng(41.889, -87.622)));*/
    }
}

