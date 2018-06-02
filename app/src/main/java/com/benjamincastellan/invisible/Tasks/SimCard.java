package com.benjamincastellan.invisible.Tasks;

import android.Manifest;
import android.annotation.SuppressLint;
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

import java.util.Date;
import java.util.List;


public class SimCard {

    private MainActivity activity;
    private LinearLayout ll;
    private ExampleFragment exampleFragment;
    final String TAG = "SimCard";

    public SimCard(MainActivity activity, LinearLayout ll) {
        this.activity = activity;
        this.ll = ll;
        exampleFragment = ExampleFragment.newInstance("Check sim card information", true);
    }

    public int execute() {
        //set the start date of the new task
        activity.addStartDate(new Date());
        TelephonyManager telephonyManager = (TelephonyManager) activity.getApplicationContext().getSystemService(activity.getApplicationContext().TELEPHONY_SERVICE);

        int simState = telephonyManager.getSimState();
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

        Boolean permission_READ_SMS = (ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED);
        Boolean permission_READ_PHONE_NUMBERS = (ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.READ_PHONE_NUMBERS) == PackageManager.PERMISSION_GRANTED);
        Boolean permission_READ_PHONE_STATE = (ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED);
        Boolean permission_ACCESS_COARSE_LOCATION = (ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED);

        if (!permission_ACCESS_COARSE_LOCATION || !permission_READ_PHONE_NUMBERS || !permission_READ_PHONE_STATE || !permission_READ_SMS) {
            Log.d(TAG, "Not the permission :( ");
            String permissionMissing = "";
            permissionMissing +=  ((!permission_READ_SMS)? "\nREAD_SMS" : "") +
                    ((!permission_READ_PHONE_NUMBERS)? "\nREAD_PHONE_NUMBERS" : "") +
                    ((!permission_READ_PHONE_STATE)? "\nREAD_PHONE_STATE":"") +
                    ((!permission_ACCESS_COARSE_LOCATION)? "\nACCESS_COARSE_LOCATION" :"");
            exampleFragment.addDetails("Need the following permissions to achieve this task:"+permissionMissing,false);
            exampleFragment.setUnknown();
        }

        checkNetworkOperatorName(telephonyManager);

        if (permission_READ_PHONE_STATE) {
            checkVersionSoftware(telephonyManager);
        }

        if (permission_ACCESS_COARSE_LOCATION) {
            List<CellInfo> allCellInfo = telephonyManager.getAllCellInfo(); //todo: analyze these information (if possible?) // need ACCESS_COARSE_LOCATION
            Log.d(TAG, "all cell info: " + allCellInfo);
        }

        if (permission_READ_PHONE_STATE || permission_READ_SMS || permission_READ_PHONE_NUMBERS) {
            String simNum = telephonyManager.getLine1Number();
            Log.d(TAG, "simNum: " + simNum);
            //todo: analyze these imformations (if possible)
        }

        activity.getFragmentManager().beginTransaction().add(ll.getId(), exampleFragment, TAG).commit();

        return 1;
    }

    void checkNetworkOperatorName(TelephonyManager telephonyManager) {
        String networkOperatorName = telephonyManager.getNetworkOperatorName();
        Log.d(TAG, "network operator: " + networkOperatorName);
        String infoNetworkOperatorName;
        boolean isItGood;
        if (networkOperatorName == null ||
                networkOperatorName.equals("Android") ||
                networkOperatorName.equals("")) {
            // probably an emulator
            // todo:what about tablets ?
            exampleFragment.setGood(false);
            infoNetworkOperatorName = "\nwhich is bad";
            isItGood = false;
        } else {
            infoNetworkOperatorName = "\nwhich is probably good";
            isItGood = true;
        }
        exampleFragment.addDetails("The name of the network operator is: " + networkOperatorName + infoNetworkOperatorName, isItGood);
    }

    void checkVersionSoftware(TelephonyManager telephonyManager) {
        @SuppressLint("MissingPermission") String softwareVersion = telephonyManager.getDeviceSoftwareVersion();
        boolean sofwareVersionIsInt = true;
        try {
            int softwareVersionInt = Integer.parseInt(softwareVersion);
        } catch (NumberFormatException nfe) {
            sofwareVersionIsInt = false;
        }
        if (!sofwareVersionIsInt) exampleFragment.setGood(false);
        Log.d(TAG, "software version: " + softwareVersion + ((sofwareVersionIsInt)? "which is good" : "which is bad, because not an integer"));
        exampleFragment.addDetails("software version: " + softwareVersion + " " + ((sofwareVersionIsInt)? "which is probably good" : "which is bad, because not an integer"),sofwareVersionIsInt);
    }
}