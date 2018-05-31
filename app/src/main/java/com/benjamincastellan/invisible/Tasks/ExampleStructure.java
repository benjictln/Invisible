package com.benjamincastellan.invisible.Tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.LinearLayout;

import com.benjamincastellan.invisible.ExampleFragment;
import com.benjamincastellan.invisible.MainActivity;

import java.util.Date;


public class ExampleStructure { // todo: replace name

    private MainActivity activity;
    private LinearLayout ll;
    private ExampleFragment exampleFragment;
    final String TAG = "Name of the task"; // todo: replace it

    public ExampleStructure(MainActivity activity, LinearLayout ll) {
        this.activity = activity;
        this.ll = ll;
        exampleFragment = ExampleFragment.newInstance("I am ...", true); // todo: replace it
    }

    public int doInBackground(Void... voids) {
        //set the start date of the new task
        activity.addStartDate(new Date());

        // do stuff

        activity.getFragmentManager().beginTransaction().add(ll.getId(), exampleFragment, TAG).commit();

        return 1;
    }
}