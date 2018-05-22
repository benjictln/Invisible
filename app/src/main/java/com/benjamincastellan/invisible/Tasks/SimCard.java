package com.benjamincastellan.invisible.Tasks;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.telephony.CellInfo;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.LinearLayout;

import com.benjamincastellan.invisible.ExampleFragment;
import com.benjamincastellan.invisible.MainActivity;

import java.util.List;


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

        // ask for permissions
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.READ_SMS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_PHONE_NUMBERS, Manifest.permission.ACCESS_COARSE_LOCATION},
                1);

        if (ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "Not the permission :( ");
            String permissionMissing = "";
            permissionMissing += (ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED)? "\nREAD_PHONE_NUMBERS" : "";
            permissionMissing += (ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED)? "\nREAD_PHONE_NUMBERS" : "";
            permissionMissing += (ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)? "\nREAD_PHONE_STATE":"";
            permissionMissing += (ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)? "\nACCESS_COARSE_LOCATION" :"";
            exampleFragment.addDetails("Need the following permissions to achieve this task:"+permissionMissing);
            exampleFragment.setUnknown();
            return null;  //todo: handle this case properly (no picture displayed ?)
        }

        checkNetworkOperatorName(telMngr);

        String softwareVersion = telMngr.getDeviceSoftwareVersion(); // need READ_PHONE_STATE
        boolean sofwareVersionIsInt = true;
        try {
            int softwareVersionInt = Integer.parseInt(softwareVersion);
        }
        catch (NumberFormatException nfe) {
            sofwareVersionIsInt = false;
        }
        //todo: handle this
        Log.d(TAG, "software version: " + softwareVersion);
        List<CellInfo> allCellInfo = telMngr.getAllCellInfo(); //todo: analyze these imformations (if possible?) // need ACCESS_COARSE_LOCATION
        Log.d(TAG, "all cell info: " + allCellInfo);
        String simNum = telMngr.getLine1Number(); //need READ_SMS || READ_PHONE_STATE || READ_PHONE_NUMBERS
        Log.d(TAG, "simNum: " + simNum);

        //todo: analyze these imformations (if possible)

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        activity.getFragmentManager().beginTransaction().add(ll.getId(), exampleFragment, TAG).commit();
    }

    void checkNetworkOperatorName(TelephonyManager telMngr) {
        String networkOperatorName = telMngr.getNetworkOperatorName();
        Log.d(TAG, "network operator: " + networkOperatorName);
        String infoNetworkOperatorName;
        if (networkOperatorName == null ||
                networkOperatorName.equals("Android") ||
                networkOperatorName.equals("")) {
            // probably an emulator
            // todo:what about tablets ?
            exampleFragment.setGood(false);
            infoNetworkOperatorName = "\nwhich is bad";
        } else {
            exampleFragment.setGood(true);
            infoNetworkOperatorName = "\nwhich is probably good";
        }
        exampleFragment.addDetails("The name of the network operator is: " + networkOperatorName + infoNetworkOperatorName);
    }

}