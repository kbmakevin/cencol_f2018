package com.example.inika.simpleviews3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    //you should define this in a string-array in strings.xml
    String[] programs = {
            "Software Engineering Technology",
            "Software Engineering Technology - Interactive Gaming",
            "Health Informatics Technology",
            "Mobile Apps Development",
            "Software Engineering Technician"
    };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create the array adapter to hold array items
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, programs);
        //create the auto text view object
        AutoCompleteTextView autoCompletTextView = (AutoCompleteTextView)
                findViewById(R.id.txtPrograms);
        //connect the adapter with autocompletetextview object
        //and set the number of characters needed to type
        autoCompletTextView.setThreshold(1);
        //bind the array to the autocomplete textview
        autoCompletTextView.setAdapter(adapter);
    }
}
