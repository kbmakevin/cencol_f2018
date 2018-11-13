package com.example.inika.simplelbsmap;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class GeoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo);
        //
        final EditText name = (EditText) findViewById(R.id.placename);
        final Geocoder coder = new Geocoder(getApplicationContext());
        final TextView results = (TextView) findViewById(R.id.result);
        final Button mapButton = (Button)findViewById(R.id.map);

        Button geocode = (Button) findViewById(R.id.geocode);
        geocode.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                String placeName = name.getText().toString();
                try
                {
                    List<Address> geocodeResults = coder.getFromLocationName(placeName, 3);
                    Iterator<Address> locations = geocodeResults.iterator();

                    String locInfo = "Results:\n";
                    double latitude = 43.6426f;
                    double longitude = -79.3871f;
                    while (locations.hasNext()) {
                        Address loc = locations.next();
                        locInfo += String.format("Location: %f, %f\n", loc.getLatitude(), loc.getLongitude());
                        if (loc.getCountryName().equals("Canada")) {
                            latitude = loc.getLatitude();
                            longitude = loc.getLongitude();
                            break;
                        }

                    }
                    results.setText(locInfo);

                    final String geoURI = String.format("geo:%f,%f", latitude, longitude);
                    //
                    mapButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            Uri geo = Uri.parse(geoURI);
                            Intent geoMap = new Intent(Intent.ACTION_VIEW, geo);
                            startActivity(geoMap);
                        }

                    });
                    mapButton.setVisibility(View.VISIBLE);

                }
                catch (IOException ioe) {
                    Log.e("GeoAddress", "Failed to get location info", ioe);
                }

            }

        });

    }

}
