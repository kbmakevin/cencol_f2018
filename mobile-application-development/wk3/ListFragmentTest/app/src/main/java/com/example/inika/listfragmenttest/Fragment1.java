package com.example.inika.listfragmenttest;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Fragment1 extends ListFragment {
    String[] primeMinisters = {
            "Sir John Alexander Macdonald",
            "Alexander Mackenzie",
            "Sir John Abbott",
            "Sir John Thompson",
            "Sir Mackenzie Bowell",
            "Sir Wilfrid Laurier",
            "Lester B. Pearson",
            "Pierre Elliott Trudeau",
            "Brian Mulroney",
            "Jean Chrétien",
            "Stephen Harper",
            "Justin Trudeau"
    };
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment1, container, false);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, primeMinisters));
    }

    public void onListItemClick(ListView parent, View v,
                                int position, long id)
    {
        Toast.makeText(getActivity(),
                "You have selected " + primeMinisters[position],
                Toast.LENGTH_SHORT).show();
    }
}