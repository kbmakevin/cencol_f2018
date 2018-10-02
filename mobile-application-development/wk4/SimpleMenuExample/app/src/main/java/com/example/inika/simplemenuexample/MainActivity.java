package com.example.inika.simplemenuexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.textView);
        //this view is associated with context menu
        //if you do a long click here, the context menu
        //will be displayed.
        registerForContextMenu(textView);
    }
    //
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }
    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.start:
                Toast.makeText(this, "You selected start!", Toast.LENGTH_LONG).show();
                break;
            case R.id.stop:
                Toast.makeText(this, "You selected stop!", Toast.LENGTH_LONG).show();
                break;
            case R.id.help:
                Toast.makeText(this, "You selected help!", Toast.LENGTH_LONG).show();
                break;
            default:
                return super.onOptionsItemSelected(item);

        }
        return true;

    }
    //
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }
    //
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.edit:
                Toast.makeText(this, "You selected Edit!", Toast.LENGTH_LONG).show();
                return true;
            case R.id.delete:
                Toast.makeText(this, "You selected Delete!", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
