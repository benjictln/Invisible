package com.benjamincastellan.invisible;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
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
                ll.removeAllViews(); // to remove the Start button
                BackgroundTask backgroundCheck=new BackgroundTask(MainActivity.this, ll);
                backgroundCheck.execute();
            }
        });
        // ask for permissions
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_SMS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_PHONE_NUMBERS, Manifest.permission.ACCESS_COARSE_LOCATION},
                1);
    }
}

