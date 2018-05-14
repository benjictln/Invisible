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

        // Add text

        TextView tv = new TextView(this);
        tv.setText("my text");
        ll.addView(tv);

        TextView tv2 = new TextView(this);
        tv2.setText("my text2");
        ll.addView(tv2);
        // Add the LinearLayout element to the ScrollView
        getFragmentManager().beginTransaction().add(ll.getId(), ExampleFragment.newInstance("I am frag 1"), "someTag1").commit();
        getFragmentManager().beginTransaction().add(ll.getId(), ExampleFragment.newInstance("I am frag 2"), "someTag2").commit();
        sv.addView(ll);

        // Display the view
        //setContentView(v);
    }
}
