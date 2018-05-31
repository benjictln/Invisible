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


public class DebuggerUsed extends AsyncTask<Void,Integer,Void> { // todo: replace name

    private MainActivity activity;
    private LinearLayout ll;
    private ExampleFragment exampleFragment;
    final String TAG = "Check for debugger";

    public DebuggerUsed(MainActivity activity, LinearLayout ll) {
        this.activity = activity;
        this.ll = ll;
        exampleFragment = ExampleFragment.newInstance("Check for debugger", true);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        // Mise à jour de la ProgressBar
        Log.d(TAG, "onProgressUpdate: " + String.valueOf(values));


    }

    @Override
    protected Void doInBackground(Void... voids) {

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
            Log.d(TAG, "calculate time spend since start of task" + String.valueOf(i) + ": " + String.valueOf((thisTime.getTime() - timeStartedTheTask.get(i).getTime()) / 60 ) + " s");
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        activity.getFragmentManager().beginTransaction().add(ll.getId(), exampleFragment, TAG).commit();
    }

}