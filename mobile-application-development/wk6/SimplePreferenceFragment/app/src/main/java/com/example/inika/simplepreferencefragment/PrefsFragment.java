package com.example.inika.simplepreferencefragment;


import android.os.Bundle;
import android.preference.PreferenceFragment;



public class PrefsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.myapppreferences);
    }
}