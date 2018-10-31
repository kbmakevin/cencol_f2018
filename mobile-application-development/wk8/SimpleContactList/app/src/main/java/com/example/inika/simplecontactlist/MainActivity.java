//
// https://www.android-examples.com/get-show-all-phone-contacts-into-listview-in-android/
//
package com.example.inika.simplecontactlist;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView ;
    //holds the contacts
    ArrayList<String> contacts ;
    //this is to populate the list view
    ArrayAdapter<String> contactsAdapter ;
    Cursor cursor ;
    String contactName, phoneNumber ;

    public  static final int RequestPermissionCode  = 1 ;
    Button display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listview1);

        display = (Button)findViewById(R.id.button1);

        contacts = new ArrayList<String>();

        enableRuntimePermission();

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getContacts();

                contactsAdapter = new ArrayAdapter<String>(
                        MainActivity.this,
                        R.layout.contact_items_listview,
                        R.id.textView, contacts
                );

                listView.setAdapter(contactsAdapter);


            }
        });

    }

    public void getContacts(){
        //call ContentResolver to query other app's data
        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null, null, null);

        while (cursor.moveToNext()) {

            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            contacts.add(contactName + " "  + ":" + " " + phoneNumber);
        }

        cursor.close();

    }

    public void enableRuntimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                MainActivity.this,
                Manifest.permission.READ_CONTACTS))
        {

            Toast.makeText(MainActivity.this,"CONTACTS permission allows us to Access CONTACTS app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.READ_CONTACTS}, RequestPermissionCode);

        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(MainActivity.this,"Permission Granted, Now your application can access CONTACTS.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(MainActivity.this,"Permission Canceled, Now your application cannot access CONTACTS.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }


}
