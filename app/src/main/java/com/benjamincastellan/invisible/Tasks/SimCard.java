package com.benjamincastellan.invisible.Tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.LinearLayout;

import com.benjamincastellan.invisible.ExampleFragment;
import com.benjamincastellan.invisible.MainActivity;


public class SimCard extends AsyncTask<Void,Integer,Void> {

    private Activity activity;
    private LinearLayout ll;
    private ExampleFragment exampleFragment;
    final String TAG = "SimCard";

    public SimCard(MainActivity activity, LinearLayout ll) {
        this.activity = activity;
        this.ll = ll;
        exampleFragment = ExampleFragment.newInstance("Check sim card information", true);
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
        TelephonyManager telMgr = (TelephonyManager) activity.getApplicationContext().getSystemService(activity.getApplicationContext().TELEPHONY_SERVICE);
        int simState = telMgr.getSimState();
        switch (simState) {
            case TelephonyManager.SIM_STATE_ABSENT:
                Log.d(TAG, "SIM_STATE_ABSENT");
                break;
            case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
                Log.d(TAG, "SIM_STATE_NETWORK_LOCKED");
                break;
            case TelephonyManager.SIM_STATE_PIN_REQUIRED:
                Log.d(TAG, "SIM_STATE_PIN_REQUIRED");
                break;
            case TelephonyManager.SIM_STATE_PUK_REQUIRED:
                Log.d(TAG, "SIM_STATE_PUK_REQUIRED");
                break;
            case TelephonyManager.SIM_STATE_READY:
                Log.d(TAG, "SIM_STATE_READY");
                break;
            case TelephonyManager.SIM_STATE_UNKNOWN:
                Log.d(TAG, "SIM_STATE_UNKNOWN");
                break;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        activity.getFragmentManager().beginTransaction().add(ll.getId(), exampleFragment, TAG).commit();
    }

}