package com.example.inika.simplemap;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    final private int REQUEST_COARSE_ACCESS = 123;
    boolean permissionGranted = false;
    LocationManager lm;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    //
    @Override
    public void onPause() {
        super.onPause();
//---remove the location listener---
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_COARSE_ACCESS);
            return;
        }else{
            permissionGranted = true;
        }
        if(permissionGranted) {
            lm.removeUpdates(locationListener);
        }
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
    /*
    If you are using both NETWORK_PROVIDER and GPS_PROVIDER,
     then you need to request only the ACCESS_FINE_LOCATION
      permission, because it includes permission for both
       providers. (Permission for ACCESS_COARSE_LOCATION
       includes permission only for NETWORK_PROVIDER
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        locationListener = new MyLocationListener();
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_COARSE_ACCESS);
            return;
        }else{
            permissionGranted = true;
        }
        if(permissionGranted) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
                    locationListener);
        }


        // Add a marker in Toronto and move the camera
        LatLng sydney = new LatLng(43.6426, -79.3871);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Toronto"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                Log.d("DEBUG","Map clicked [" + point.latitude +
                        " / " + point.longitude + "]");
                //
                Geocoder geoCoder = new Geocoder(
                        getBaseContext(), Locale.getDefault());
                try {
                    List<Address> addresses = geoCoder.getFromLocation(
                            point.latitude,point.longitude,1);
                    String add = "";
                    if (addresses.size() > 0)
                    {
                        for (int i=0; i<addresses.get(0).
                                getMaxAddressLineIndex();
                             i++)
                            add += addresses.get(0).getAddressLine(i) + "\n";
                    }
                    Toast.makeText(getBaseContext()
                            , add, Toast.LENGTH_SHORT).show();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //


        });
    }
    //
    private class MyLocationListener implements LocationListener
    {
        public void onLocationChanged(Location loc) {
            if (loc != null) {
                Toast.makeText(getBaseContext(),
                        "Location changed : Lat: " + loc.getLatitude() +
                                " Lng: " + loc.getLongitude(),
                        Toast.LENGTH_SHORT).show();
                LatLng p = new LatLng(
                        (int) (loc.getLatitude()),
                        (int) (loc.getLongitude()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(p));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(7));
            }
        }
        //---called when the provider is disabled---
        public void onProviderDisabled(String provider) {
            Toast.makeText(getBaseContext(),
                    provider + " disabled",
                    Toast.LENGTH_SHORT).show();
        }
        //---called when the provider is enabled---
        public void onProviderEnabled(String provider) {
            Toast.makeText(getBaseContext(),
                    provider + " enabled",
                    Toast.LENGTH_SHORT).show();
        }
        //---called when there is a change in the provider status---
        public void onStatusChanged(String provider, int status,
                                    Bundle extras) {
            String statusString = "";
            switch (status) {
                case android.location.LocationProvider.AVAILABLE:
                    statusString = "available";
                case android.location.LocationProvider.OUT_OF_SERVICE:
                    statusString = "out of service";
                case android.location.LocationProvider.TEMPORARILY_UNAVAILABLE:
                    statusString = "temporarily unavailable";
            }
            Toast.makeText(getBaseContext(),
                    provider + " " + statusString,
                    Toast.LENGTH_SHORT).show();
        }
    }
    //


    //You can now zoom into the map
    //by clicking the numeric 3 key on the emulator.
    // To zoom out of the map, click the numeric 1 key
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_3:
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
                break;
            case KeyEvent.KEYCODE_1:
                mMap.animateCamera(CameraUpdateFactory.zoomOut());
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
    //

}
