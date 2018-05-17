package com.benjamincastellan.invisible.Tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.LinearLayout;

import com.benjamincastellan.invisible.ExampleFragment;
import com.benjamincastellan.invisible.MainActivity;

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

        activity.getFragmentManager().beginTransaction().add(ll.getId(), exampleFragment, "someTag1").commit();

    }

    @Override
    protected Void doInBackground(Void... voids) {
        publishProgress(0);
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
    }

}
