package com.example.inika.simplewebviewexample;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class FullScreenWebViewActivity extends Activity {
	private static final String DEBUG_TAG = "FullScreenWebViewActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		WebView wv = new WebView(this);
		setContentView(wv);

		wv.loadUrl("http://www.centennialcollege.ca/");
		wv.setInitialScale(50);
	}
}