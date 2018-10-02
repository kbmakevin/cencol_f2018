package com.example.inika.simpleviews2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    static int progress;
    ProgressBar progressBar;
    int progressStatus = 0;
    //create the Handler object in the UI thread
    Handler handler = new Handler();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //show the UI
        setContentView(R.layout.activity_main);

        progress = 0;
        //create the progress bar object
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setMax(1000);

        //the update part
        //---do some work in background thread---
        new Thread(new Runnable() //create a new thread
        {
            //implement run method
            //this is where you do the thread's job
            public void run()
            {
                //�-do some work here�-
                while (progressStatus < 5000)
                {
                    //get the updated progess value
                    progressStatus = doSomeWork();

                    //�-Update the progress bar�-
                    //you have to do that from within UI thread
                    //by posting a message to Handler object
                    handler.post(new Runnable()
                    {
                        //this thread updates the progress status
                        public void run() {
                            //set the updated progres value
                            progressBar.setProgress(progressStatus);
                        }
                    });
                }

                //---hides the progress bar--- update the UI
                handler.post(new Runnable()
                {
                    public void run()
                    {
                        //---0 - VISIBLE; 4 - INVISIBLE; 8 - GONE---
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }

            //---do some long running work here---
            private int doSomeWork()
            {
                try {
                    //---simulate doing some work---
                    Thread.sleep(100); //one second sleep
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                return ++progress;
            }
        }).start(); //starts the background thread
    }
}
