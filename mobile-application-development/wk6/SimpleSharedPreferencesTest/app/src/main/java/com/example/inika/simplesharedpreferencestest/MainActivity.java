package com.example.inika.simplesharedpreferencestest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);

    }

    public void sendMessage(View view) {
        // Do something in response to button
        SharedPreferences myPreference =
                getSharedPreferences("MyGoodMemory", 0);
        //prepare it for edit by creating and Edit object
        SharedPreferences.Editor prefEditor = myPreference.edit();
        //store a string in memory
        prefEditor.putString("OurString", editText.getText().toString());
        //commit the transaction
        prefEditor.commit();
        //call intent only to show the next activity
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }



}
