package com.example.inika.simplelayoutsexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
    }

    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.btnLinear:
                setContentView(R.layout.linear_layout);
                break;
            case R.id.btnFrame:
                setContentView(R.layout.frame_layout);
                break;
            case R.id.btnTable:
                setContentView(R.layout.table_layout);
                break;
            case R.id.btnRelative:
                setContentView(R.layout.relative_layout);
                break;
            case R.id.btnAbsolute:
                setContentView(R.layout.absolute_layout);
                break;
            case R.id.btnScroll:
                setContentView(R.layout.scroll_layout);
                break;
            default: setContentView(R.layout.main_layout);
        }
    }

    @Override
    public void onBackPressed(){
        //show the previous view
        setContentView(R.layout.main_layout);

    }
}
