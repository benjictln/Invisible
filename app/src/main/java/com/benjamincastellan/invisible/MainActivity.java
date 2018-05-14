package com.benjamincastellan.invisible;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View v = inflater.inflate(R.layout.profile_list, null);

        // Find the ScrollView
        ScrollView sv = (ScrollView) findViewById(R.id.scrollView1);

        // Create a LinearLayout element
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setId(12345);
        sv.addView(ll);



        // Add the LinearLayout element to the ScrollView
        getFragmentManager().beginTransaction().add(ll.getId(), ExampleFragment.newInstance("I am frag 1",true), "someTag1").commit();
        getFragmentManager().beginTransaction().add(ll.getId(), ExampleFragment.newInstance("I am frag 2", false), "someTag2").commit();




        // Add the LinearLayout element to the ScrollView
        getFragmentManager().beginTransaction().add(ll.getId(), ExampleFragment.newInstance("I am frag 1",true), "someTag1").commit();

        // Display the view
        //setContentView(v);
    }
}
