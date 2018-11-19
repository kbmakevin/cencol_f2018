package com.example.inika.simplewebviewexample;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class WebViewActivity extends Activity {
    private static final String DEBUG_TAG = "WebViewActivity";
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);
        final EditText et = (EditText) findViewById(R.id.url);
        final WebView wv = (WebView) findViewById(R.id.web_content);

        wv.loadUrl("http://www.centennialcollege.ca/");
        ///
        Button go = (Button) findViewById(R.id.go_button);
        go.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                wv.loadUrl(et.getText().toString());

            }
        });
        WebViewClient webClient = new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                Log.v(DEBUG_TAG, "Page finished loading");
                super.onPageFinished(view, url);
                title = wv.getTitle();
                Log.v(DEBUG_TAG, title);


                
            }
        };
        
        // ... and listen for changes
        WebChromeClient webChrome = new WebChromeClient() {
            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                Log.v(DEBUG_TAG, "Got new icon");
                super.onReceivedIcon(view, icon);
                //favImage.setImageDrawable(new BitmapDrawable(icon));
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                Log.v(DEBUG_TAG, "Got new title");
                super.onReceivedTitle(view, title);
            }

        };
        wv.setWebViewClient(webClient);
        wv.setWebChromeClient(webChrome);
        wv.setInitialScale(200);
    }
}
