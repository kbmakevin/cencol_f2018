package com.example.inika.simpleframedanimation;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private AnimationDrawable mframeAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        // Handle Start Button
        findViewById(R.id.ButtonStart).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.startAnimation();
            }
        });

        // Handle Stop Button
        final Button offButton = (Button) findViewById(R.id.ButtonStop);
        offButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stopAnimation();
            }
        });
    }

    private void startAnimation() {
        ImageView img = (ImageView) findViewById(R.id.ImageView_Boy);
        Drawable[] frames = new Drawable[11];
        frames[0] = getResources().getDrawable(R.drawable.frame0);
        frames[1] = getResources().getDrawable(R.drawable.frame1);
        frames[2] = getResources().getDrawable(R.drawable.frame2);
        frames[3] = getResources().getDrawable(R.drawable.frame3);
        frames[4] = getResources().getDrawable(R.drawable.frame4);
        frames[5] = getResources().getDrawable(R.drawable.frame5);
        frames[6] = getResources().getDrawable(R.drawable.frame6);
        frames[7] = getResources().getDrawable(R.drawable.frame7);
        frames[8] = getResources().getDrawable(R.drawable.frame8);
        frames[9] = getResources().getDrawable(R.drawable.frame9);
        frames[10] = getResources().getDrawable(R.drawable.frame10);

        // Get the background, which has been compiled to an AnimationDrawable object.
        mframeAnimation = new AnimationDrawable();
        for (Drawable frame : frames) {
            mframeAnimation.addFrame(frame, 200);
        }
        mframeAnimation.setOneShot(false);    // loop continuously
        img.setBackgroundDrawable(mframeAnimation);
        mframeAnimation.start();
    }

    private void stopAnimation() {
        mframeAnimation.stop();
    }
}
