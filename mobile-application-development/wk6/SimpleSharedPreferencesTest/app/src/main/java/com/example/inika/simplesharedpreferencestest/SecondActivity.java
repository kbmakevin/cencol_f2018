package com.example.inika.simplesharedpreferencestest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.widget.TextView;
//
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //retrieving from shared preferences
        SharedPreferences myPref = getSharedPreferences("MyGoodMemory", MODE_PRIVATE);
        String myString = myPref.getString("OurString","");
        TextView tView = (TextView)findViewById(R.id.tView);
        tView.setText("Retrieving from shared preferences: "+myString);



    }

}
