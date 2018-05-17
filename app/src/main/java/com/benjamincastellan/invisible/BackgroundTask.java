package com.benjamincastellan.invisible;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class BackgroundTask extends AsyncTask<Void, Integer, Void>
{

    private Activity activity;
    private int nb_operation;
    private LinearLayout ll;

    public BackgroundTask(MainActivity activity, LinearLayout ll) {
        this.activity = activity;
        nb_operation = 0;
        this.ll = ll;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(activity.getApplicationContext(), "Starting operation " + String.valueOf(++nb_operation), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onProgressUpdate(Integer... values){
        super.onProgressUpdate(values);
        // Mise à jour de la ProgressBar
        Log.d(TAG, "onProgressUpdate: " + String.valueOf(values));


        activity.getFragmentManager().beginTransaction().add(ll.getId(), ExampleFragment.newInstance("I am frag 1",true), "someTag1").commit();

    }

    @Override
    protected Void doInBackground(Void... arg0) {

        int progress;
        for (progress=0;progress<=10;progress++)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(progress);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        Toast.makeText(activity.getApplicationContext(), "Operation finished", Toast.LENGTH_LONG).show();
    }
}
