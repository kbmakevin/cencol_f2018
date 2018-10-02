package com.example.inika.spinnertest1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //create the spinner object
        final Spinner spin = (Spinner) findViewById(R.id.provinces_spinner);
        //create a button object
        final Button submit = (Button)findViewById(R.id.submit);
        //handle the  click event
        submit.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                //get the spinner view as text view
                TextView text_sel = (TextView)spin.getSelectedView();
                //get the text from the spinner view
                Toast.makeText(MainActivity.this, "\n Province = "+text_sel.getText(), Toast.LENGTH_SHORT).show();
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
