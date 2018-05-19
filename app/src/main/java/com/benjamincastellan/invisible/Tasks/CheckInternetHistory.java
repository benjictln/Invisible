package com.benjamincastellan.invisible.Tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.LinearLayout;

import com.benjamincastellan.invisible.ExampleFragment;
import com.benjamincastellan.invisible.MainActivity;
import com.benjamincastellan.invisible.R;

import static android.content.ContentValues.TAG;

public class CheckInternetHistory extends AsyncTask<Void,Integer,Void> {


    private Activity activity;
    private LinearLayout ll;
    private ExampleFragment exampleFragment;

    public CheckInternetHistory(MainActivity activity, LinearLayout ll) {
        this.activity = activity;
        this.ll = ll;
        exampleFragment = ExampleFragment.newInstance("I am checking internet", true);
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values){
        super.onProgressUpdate(values);
        // Mise à jour de la ProgressBar
        Log.d(TAG, "onProgressUpdate: " + String.valueOf(values));


    }

    @Override
    protected Void doInBackground(Void... voids) {
        publishProgress(0);
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        exampleFragment.setGood(true);
        activity.getFragmentManager().beginTransaction().add(ll.getId(), exampleFragment, "someTag2").commit();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
