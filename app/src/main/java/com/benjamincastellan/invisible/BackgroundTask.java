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

    private MainActivity activity;
    private int nb_operation_done;
    private LinearLayout ll;
    private final int nb_tasks_available = 5;
    private LaunchTasks launchTasks;

    public BackgroundTask(MainActivity activity, LinearLayout ll) {
        this.activity = activity;
        nb_operation_done = 0;
        this.ll = ll;
        launchTasks = new LaunchTasks();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(activity.getApplicationContext(), "Starting operation " + String.valueOf(++nb_operation_done), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onProgressUpdate(Integer... values){
        super.onProgressUpdate(values);
        // Mise à jour de la ProgressBar
        Log.d(TAG, "onProgressUpdate: " + String.valueOf(values[0]));
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        for (nb_operation_done=0;nb_operation_done<nb_tasks_available;nb_operation_done++)
        {
            launchTasks.execute(nb_operation_done, activity, ll);
            publishProgress(nb_operation_done);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        Toast.makeText(activity.getApplicationContext(), "Operation finished", Toast.LENGTH_LONG).show();
    }
}
