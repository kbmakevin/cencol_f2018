package net.learn2develop.DialogFragmentExample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class DialogFragmentExampleActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //create and show a dialog by calling the static method of Fragment1
        Fragment1 dialogFragment = Fragment1.newInstance(
                "Are you sure you want to do this?");
        dialogFragment.show(getFragmentManager(), "dialog");
    }
    public void doPositiveClick() {
        //---perform steps when user clicks on OK---
        Log.d("DialogFragmentExample", "User clicks on OK");
    }
    public void doNegativeClick() {
        //---perform steps when user clicks on Cancel---
        Log.d("DialogFragmentExample", "User clicks on Cancel");
    }

} //end of main activity