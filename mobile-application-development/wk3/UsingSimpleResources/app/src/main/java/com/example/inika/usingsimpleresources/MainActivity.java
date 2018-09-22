package com.example.inika.usingsimpleresources;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import android.support.v4.content.ContextCompat;
//
public class MainActivity extends AppCompatActivity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //show the UI
        setContentView(R.layout.activity_main);
        //read the resources defined in strings, colors, dimens files
        String myString = getResources().getString(R.string.display);
        //int myColor = getResources().getColor(R.color.niceTextColor);
        int myColor= ContextCompat.getColor(this, R.color.niceTextColor);
        float myDimen = getResources().getDimension(R.dimen.textPointSize);
        ColorDrawable myDraw = (ColorDrawable)getResources().getDrawable(R.drawable.red_rect);
        //
        //create the Java object for your view
        ImageView imgView = (ImageView)findViewById(R.id.imageView1);
        //get the flag image from resources
        //BitmapDrawable bitmapFlag = (BitmapDrawable)
        //        getResources().getDrawable(R.drawable.flag);
        BitmapDrawable bitmapFlag = (BitmapDrawable)ContextCompat.getDrawable(this, R.drawable.flag);
        //
        //display the drawable on image view
        imgView.setImageDrawable(myDraw);
        //display the image on image view
        //imgView.setImageDrawable(bitmapFlag);
        String[] notFlavors = getResources().getStringArray(R.array.flavors);
        TextView txtTitle = (TextView)findViewById(R.id.txtView);
        txtTitle.setTextColor(myColor);
        //
        TextView tv = (TextView)findViewById(R.id.txtView);
        tv.setTextSize(myDimen);
        tv.setTextColor(myColor);
        tv.setText(myString);
        //

    }
}
