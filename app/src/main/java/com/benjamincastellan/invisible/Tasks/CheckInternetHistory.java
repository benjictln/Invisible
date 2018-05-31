package com.benjamincastellan.invisible.Tasks;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.Browser;
import android.util.Log;
import android.widget.LinearLayout;

import com.benjamincastellan.invisible.ExampleFragment;
import com.benjamincastellan.invisible.MainActivity;
import com.benjamincastellan.invisible.R;

import android.provider.Browser;
import android.net.Uri;
import android.database.Cursor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;


public class CheckInternetHistory extends AsyncTask<Void,Integer,Void> {

    final String TAG = "Browser History Task";
    private MainActivity activity;
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
        //set the start date of the new task
        activity.addStartDate(new Date());
    }

    @Override
    protected void onProgressUpdate(Integer... values){
        super.onProgressUpdate(values);
        // Mise à jour de la ProgressBar
        Log.d(TAG, "onProgressUpdate: " + String.valueOf(values));


    }

    @Override
    protected Void doInBackground(Void... voids) {
        new Thread(){
            @Override
            public void run(){
                URL url = null;
                try {
                    url = new URL("Chrome://history");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                URLConnection conn = null;
                try {
                    conn = url.openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                InputStreamReader streamReader = null;
                try {
                    streamReader = new InputStreamReader(conn.getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                BufferedReader br = new BufferedReader(streamReader);
                StringBuilder sb = new StringBuilder();
                String line = null;
                try {
                    while((line = br.readLine()) != null) {
                        sb.append(line);sb.append("\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, sb.toString());
            }
        }.start();
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        exampleFragment.setGood(true);
        /*for (int i = 0; i < 10; i++) {
            exampleFragment.addDetails("test");
        }*/
        activity.getFragmentManager().beginTransaction().add(ll.getId(), exampleFragment, TAG).commit();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    

}
