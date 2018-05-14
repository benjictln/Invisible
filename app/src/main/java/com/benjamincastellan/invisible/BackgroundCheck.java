package com.benjamincastellan.invisible;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class BackgroundCheck extends AsyncTask<Void, Integer, Void>
{

    private Context context;
    private int nb_operation;

    public BackgroundCheck(Context context) {
        this.context = context;
        nb_operation = 0;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(context.getApplicationContext(), "Starting operation " + String.valueOf(++nb_operation), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onProgressUpdate(Integer... values){
        super.onProgressUpdate(values);
        // Mise à jour de la ProgressBar
        Log.d(TAG, "onProgressUpdate: " + String.valueOf(values));
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
        Toast.makeText(context.getApplicationContext(), "Operation finished", Toast.LENGTH_LONG).show();
    }
}
