package com.benjamincastellan.invisible.Tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Debug;
import android.util.Log;
import android.widget.LinearLayout;

import com.benjamincastellan.invisible.ExampleFragment;
import com.benjamincastellan.invisible.MainActivity;

import java.util.ArrayList;
import java.util.Date;


public class DebuggerUsed { // todo: replace name

    private MainActivity activity;
    private LinearLayout ll;
    private ExampleFragment exampleFragment;
    final String TAG = "Check for debugger";

    public DebuggerUsed(MainActivity activity, LinearLayout ll) {
        this.activity = activity;
        this.ll = ll;
        exampleFragment = ExampleFragment.newInstance("Check for debugger", true);
    }

    public int execute() {

        Log.d(TAG, "doInBackground: isDebuggerConnected ->" + String.valueOf(Debug.isDebuggerConnected()));
        if (Debug.isDebuggerConnected()) {
            exampleFragment.setGood(false);
            exampleFragment.addDetails("The debugger was easily spotted", false);
        } else {
            exampleFragment.addDetails("the debugger was not easily spotted", true);
        }

        ArrayList<Date> timeStartedTheTask = activity.getTimeStartedTheTask();
        Date thisTime = new Date();
        for (int i = 0; i < timeStartedTheTask.size(); i++) {
            long timeSpent = thisTime.getTime() - timeStartedTheTask.get(i).getTime();
            Log.d(TAG, "calculate time spent since start of task" + String.valueOf(i) + ": " + String.valueOf(timeSpent) + " ms");
            switch (i){
                case 0:
                    if (timeSpent > 20000 || timeSpent < 1000) {
                        exampleFragment.setGood(false);
                        exampleFragment.addDetails("Time spent since task " + String.valueOf(i) + " is: " + String.valueOf(timeSpent) + " ms.", false);
                    } else {
                        exampleFragment.addDetails("Time spent since task " + String.valueOf(i) + " is: " + String.valueOf(timeSpent) + " ms.", true);
                    }
                    //is about 1500 ms on the emulator, 5000ms on a fast phone depends on the number of apps also..
                    break;
                case 1:
                    if (timeSpent > 500 || timeSpent < 50) {
                        exampleFragment.setGood(false);
                        exampleFragment.addDetails("Time spent since task " + String.valueOf(i) + " is: " + String.valueOf(timeSpent) + " ms.", false);
                    } else {
                        exampleFragment.addDetails("Time spent since task " + String.valueOf(i) + " is: " + String.valueOf(timeSpent) + " ms.", true);
                    }

                    //is about 88 ms, 140 ms on a fast phone
                    break;
                case 2:
                    if (timeSpent > 400 || timeSpent < 35) {
                        exampleFragment.setGood(false);
                        exampleFragment.addDetails("Time spent since task " + String.valueOf(i) + " is: " + String.valueOf(timeSpent) + " ms.", false);
                    } else {
                        exampleFragment.addDetails("Time spent since task " + String.valueOf(i) + " is: " + String.valueOf(timeSpent) + " ms.", true);
                    }
                    //is about 67 ms, 100ms on a fast phone
                    break;
                default:
                    if (timeSpent > 40 || timeSpent < 3) {
                        exampleFragment.setGood(false);
                        exampleFragment.addDetails("Time spent since task " + String.valueOf(i) + " is: " + String.valueOf(timeSpent) + " ms.", false);
                    } else {
                        exampleFragment.addDetails("Time spent since task " + String.valueOf(i) + " is: " + String.valueOf(timeSpent) + " ms.", true);
                    }
                    // it is about 24 ms, 9ms on a fast phone

            }
        }
        activity.getFragmentManager().beginTransaction().add(ll.getId(), exampleFragment, TAG).commit();

        return 1;
    }

}