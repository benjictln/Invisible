package com.benjamincastellan.invisible.Tasks;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.LinearLayout;

import com.benjamincastellan.invisible.ExampleFragment;
import com.benjamincastellan.invisible.MainActivity;


public class SimCard extends AsyncTask<Void, Integer, Void> {

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
        TelephonyManager telMngr = (TelephonyManager) activity.getApplicationContext().getSystemService(activity.getApplicationContext().TELEPHONY_SERVICE);
        int simState = telMngr.getSimState();
        String operator = telMngr != null ? telMngr.getSimOperator() : null;  //310260 is emulator android
        Log.d(TAG, "Operator: " + operator); //todo: get country from operator

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


        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.READ_SMS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_PHONE_NUMBERS},
                1);
        if (ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            Log.d(TAG, "Not the permission :( ");

            return null;  //todo: handle this case properly (no picture displayed ?)
        }

        String simNum = telMngr.getLine1Number();
        Log.d(TAG, "simNum: " + simNum);


        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        activity.getFragmentManager().beginTransaction().add(ll.getId(), exampleFragment, TAG).commit();
    }

}