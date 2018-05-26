package com.benjamincastellan.invisible.Tasks;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.LinearLayout;

import com.benjamincastellan.invisible.ExampleFragment;
import com.benjamincastellan.invisible.MainActivity;

import java.util.Arrays;


public class BuildInformation extends AsyncTask<Void,Integer,Void> {

    private Activity activity;
    private LinearLayout ll;
    private ExampleFragment exampleFragment;
    final String TAG = "BuildInformation";

    public BuildInformation(MainActivity activity, LinearLayout ll) {
        this.activity = activity;
        this.ll = ll;
        exampleFragment = ExampleFragment.newInstance("Checking the build information", true);
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
        //logValuesBuild();
        Boolean permission_READ_PHONE_STATE = (ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED);
        Log.d(TAG, "doInBackground: " + Build.BOARD + "\n" //unknown for the emulator, MHA for my phone
                        + Build.BOOTLOADER + "\n"   //unknown for the emulator, unknown for my phone
                        + Build.BRAND + "\n"  // google for the emulator, HUAWEI for my phone
                        + Build.DEVICE + "\n"  //generic_x86 for the emulator, HWMHA for my phone
                        + Build.DISPLAY + "\n" // OSM1.180201.007 fot the emulator, MHA-L29 8.0.0.368(C636) for my phone
                        + Build.FINGERPRINT + "\n"  //google/sdk_gphone_x86/generic_x86:8.1.0/OSM1.180201.007/4586646:user/release-keys for the emulator, HUAWEI/MHA-L29/HWMHA:8.0.0/HUAWEIMHA-L29/368(C636):user/release-keys for my phone
                        + Build.HARDWARE + "\n" // ranchu for the emulator, hi3660 for my phone
                        + Build.HOST + "\n" // abfarm281 for the emulator, WUH1000129002 for my phone
                        + Build.ID + "\n" // OSM1.180201.007 for the emulator, HUAWEIMHA-L29 for my phone
                        + Build.MANUFACTURER + "\n" // Google for the emulator, HUAWEI for my phone
                        + Build.MODEL + "\n" // Android SDK built for x86 for the emulator, MHA-L29 for my phone
                        + Build.PRODUCT + "\n" // sdk_gphone_x86 for the emulator, MHA-L29 for my phone
                        + " type " + Build.TYPE + "\n" // user for the emulator, user for my phone
                        + " user " + Build.USER + "\n" // android-build for the emulator, test for my phone
                        + Build.getRadioVersion() + "\n" // nothing for the emulator, 21C30B323S001C000,21C30B323S001C000 for my phone
        );

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Log.d(TAG, "doInBackground: " + Arrays.toString(Build.SUPPORTED_32_BIT_ABIS) + "\n" // [x86] for the emulator, [armeabi-v7a, armeabi] for my phone
                    + Arrays.toString(Build.SUPPORTED_64_BIT_ABIS)); // [] for the emulator, [arm64-v8a] for my phone);
        }
        //+  + "\n"
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) { // unknown for the emulator, a value for my phone
            Log.d(TAG, "doInBackground: " + Build.SERIAL);
        }else if (permission_READ_PHONE_STATE) {
            Log.d(TAG, "doInBackground: " + Build.getSerial());
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        activity.getFragmentManager().beginTransaction().add(ll.getId(), exampleFragment, TAG).commit();
    }

    private void logValuesBuild() {
        Boolean permission_READ_PHONE_STATE = (ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED);

        Log.d(TAG, "doInBackground: " + Build.BOARD + "\n" //unknown for the emulator, MHA for my phone
                + Build.BOOTLOADER + "\n"   //unknown for the emulator, unknown for my phone
                + Build.BRAND + "\n"  // google for the emulator, HUAWEI for my phone
                + Build.DEVICE + "\n"  //generic_x86 for the emulator, HWMHA for my phone
                + Build.DISPLAY + "\n" // OSM1.180201.007 fot the emulator, MHA-L29 8.0.0.368(C636) for my phone
                + Build.FINGERPRINT + "\n"  //google/sdk_gphone_x86/generic_x86:8.1.0/OSM1.180201.007/4586646:user/release-keys for the emulator, HUAWEI/MHA-L29/HWMHA:8.0.0/HUAWEIMHA-L29/368(C636):user/release-keys for my phone
                + Build.HARDWARE + "\n" // ranchu for the emulator, hi3660 for my phone
                + Build.HOST + "\n" // abfarm281 for the emulator, WUH1000129002 for my phone
                + Build.ID + "\n" // OSM1.180201.007 for the emulator, HUAWEIMHA-L29 for my phone
                + Build.MANUFACTURER + "\n" // Google for the emulator, HUAWEI for my phone
                + Build.MODEL + "\n" // Android SDK built for x86 for the emulator, MHA-L29 for my phone
                + Build.PRODUCT + "\n" // sdk_gphone_x86 for the emulator, MHA-L29 for my phone
                + " type " + Build.TYPE + "\n" // user for the emulator, user for my phone
                + " user " + Build.USER + "\n" // android-build for the emulator, test for my phone
                + Build.getRadioVersion() + "\n" // nothing for the emulator, 21C30B323S001C000,21C30B323S001C000 for my phone
        );

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Log.d(TAG, "doInBackground: " + Arrays.toString(Build.SUPPORTED_32_BIT_ABIS) + "\n" // [x86] for the emulator, [armeabi-v7a, armeabi] for my phone
                    + Arrays.toString(Build.SUPPORTED_64_BIT_ABIS)); // [] for the emulator, [arm64-v8a] for my phone);
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) { // unknown for the emulator, a value for my phone
            Log.d(TAG, "doInBackground: " + Build.SERIAL);
        }else if (permission_READ_PHONE_STATE) {
            Log.d(TAG, "doInBackground: " + Build.getSerial());
        }
    }
}