package com.example.inika.fragmentsinteractionexample;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, 
    ViewGroup container, Bundle savedInstanceState) {
        //---Inflate the layout for this fragment---
        return inflater.inflate(
            R.layout.fragment2, container, false);
    }
    
    //the fragment starts
    @Override
    public void onStart() {
        super.onStart();        
        //create a Button object from btnGetText button
        //which is in Fragment2 view
        //need an activity object to call findViewById
        Button btnGetText = (Button) 
            getActivity().findViewById(R.id.btnGetText);
        //handle the click event usinga an onClickListener
        //1- register the button with the onClickListener
        btnGetText.setOnClickListener(new View.OnClickListener() {
            //2- implement the event handler method
            //in an anonymous class
            public void onClick(View v) {
                //create a TextView object
                TextView lbl = (TextView)
                    getActivity().findViewById(R.id.lblFragment1);
                //show the caption of the text view on a Toast message
                Toast.makeText(getActivity(), lbl.getText(), 
                    Toast.LENGTH_SHORT).show();                
            }
        });        
    }

}
