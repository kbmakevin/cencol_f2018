package com.example.inika.intentsexample;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    int request_Code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClickWebBrowser(View view) {
		/*
		Intent i = new
				Intent(android.content.Intent.ACTION_VIEW,
						Uri.parse("http://www.amazon.com"));
		*/
		/*
		Intent i = new
				Intent("android.intent.action.VIEW",
						Uri.parse("http://www.amazon.com"));
		*/

        Intent i = new
                Intent("android.intent.action.VIEW");
        i.setData(Uri.parse("https://www.centennialcollege.ca/"));
        startActivity(i);
    }

    public void onClickMakeCalls(View view) {
        Intent i = new
                Intent(android.content.Intent.ACTION_DIAL,
                Uri.parse("tel:+651234567"));
        startActivity(i);

		/*
		Intent i = new
				Intent(android.content.Intent.ACTION_CALL,
						Uri.parse("tel:+651234567"));
		startActivity(i);
		*/
    }

    public void onClickShowMap(View view) {
        Intent i = new
                Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("geo:43.6426,-79.3871"));
        startActivity(i);

    }

    public void onClickLaunchMyBrowser(View view) {
        //needs internet permission in manifest file
		Intent i = new Intent(this,MyBrowserActivity.class);
        i.setData(Uri.parse("https://www.centennialcollege.ca/"));
        startActivity(i);


    }

}
