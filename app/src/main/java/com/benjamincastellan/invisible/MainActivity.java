package com.benjamincastellan.invisible;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the ScrollView
        ScrollView sv = (ScrollView) findViewById(R.id.scrollView1);

        // Create a LinearLayout element
        final LinearLayout ll = (LinearLayout) findViewById(R.id.container_layout);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setId(12345);

        // Create the button that starts all the different tasks
        Button mButton = (Button) findViewById(R.id.btnLaunch);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                BackgroundTask backgroundCheck=new BackgroundTask(MainActivity.this, ll);
                backgroundCheck.execute();
            }
        });

    }
}

