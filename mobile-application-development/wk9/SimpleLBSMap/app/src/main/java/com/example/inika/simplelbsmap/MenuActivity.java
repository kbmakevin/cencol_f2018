package com.example.inika.simplelbsmap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    String[] activities;
    ListView lstView;
    Intent intent;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //
        lstView = (ListView)findViewById(R.id.android_list);
        TextView textView = new TextView(getApplicationContext());
        textView.setText("Testing Location Based Services");

        lstView.addHeaderView(textView);
        lstView.setChoiceMode(ListView.CHOICE_MODE_NONE);
        lstView.setTextFilterEnabled(true);

        activities = getResources().getStringArray(R.array.activities);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, activities);
        // Assign adapter to ListView
        lstView.setAdapter(adapter);

        // ListView Item Click Listener
        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                //String item = (String) lstView.getItemAtPosition(position);
                switch(itemPosition)
                {
                    case 1:
                        intent = new Intent(MenuActivity.this, GeoActivity.class);
                        break;
                    case 2:
                        intent = new Intent(MenuActivity.this, MapsActivity.class);
                        break;
                    case 3:
                        intent = new Intent(MenuActivity.this, GPSActivity.class);
                        break;

                }
                // show next activity
                intent.putExtra("activityName",(String) lstView.getItemAtPosition(position));
                startActivity(intent);

            }

        });

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
