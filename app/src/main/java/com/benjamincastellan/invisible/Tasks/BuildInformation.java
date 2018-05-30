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

import java.util.ArrayList;
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
        // todo: test with genymotion and other emulators
        logValuesBuild();
        Boolean permission_READ_PHONE_STATE = (ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED);
        int nb_of_suspicious_values = 0;
        ArrayList<String> suspiciousValues = new ArrayList<String>();
        if (Build.BOARD.equals("unknown")) {
            nb_of_suspicious_values++;
            suspiciousValues.add("Build.BOARD = \"unknown\"");
        }
        if (Build.DEVICE.contains("x86") || Build.DEVICE.contains("generic")) {
            nb_of_suspicious_values++;
            suspiciousValues.add("Build.DEVICE = \"" + Build.DEVICE + "\"");
        }
        if (Build.DISPLAY.equals("OSM1.180201.007")) {
            nb_of_suspicious_values++;
            suspiciousValues.add("Build.DISPLAY = \"OSM1.180201.007\"");
        }
        if (Build.FINGERPRINT.equals("google/sdk_gphone_x86/generic_x86:8.1.0/OSM1.180201.007/4586646:user/release-keys")) {
            nb_of_suspicious_values++;
            suspiciousValues.add("Build.FINGERPRINT = \"google/sdk_gphone_x86/generic_x86:8.1.0/OSM1.180201.007/4586646:user/release-keys\"");
        }
        if (Build.HARDWARE.equals("ranchu") || Build.HARDWARE.equals("goldfish")) {
            nb_of_suspicious_values++;
            suspiciousValues.add("Build.HARDWARE = \"" + Build.HARDWARE + "\"");
        }
        if (Build.HOST.equals("abfarm281")) {
            nb_of_suspicious_values++;
            suspiciousValues.add("Build.HOST = \"abfarm281\"");
        }
        if (Build.ID.equals("OSM1.180201.007")) {
            nb_of_suspicious_values++;
            suspiciousValues.add("Build.ID = \"OSM1.180201.007\"");
        }
        if (Build.MODEL.contains("Android SDK built")) {
            nb_of_suspicious_values++;
            suspiciousValues.add("Build.MODEL = \"" + Build.HARDWARE + "\"");
        }
        if (Build.PRODUCT.equals("sdk_gphone_x86")) {
            nb_of_suspicious_values++;
            suspiciousValues.add("Build.PRODUCT = \"sdk_gphone_x86\"");
        }
        if (Build.USER.equals("android-build")) {
            nb_of_suspicious_values++;
            suspiciousValues.add("Build.USER = \"android-build\"");
        }
        if (Build.getRadioVersion().equals("")) {
            nb_of_suspicious_values++;
            suspiciousValues.add("Build.getRadioVersion() = \"\"");
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (Arrays.toString(Build.SUPPORTED_32_BIT_ABIS).equals("[x86]") && Build.SUPPORTED_64_BIT_ABIS.length == 0) {
                nb_of_suspicious_values++;
                suspiciousValues.add("Build.SUPPORTED_32_BIT_ABIS = \"[x86]\"\nBuild.SUPPORTED_64_BIT_ABIS is empty");
            }
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) { // unknown for the emulator google, a value for my phone
            if (Build.SERIAL.equals("")) {
                nb_of_suspicious_values++;
                suspiciousValues.add("Build.SERIAL = \"\"");
            }
        } else if (permission_READ_PHONE_STATE) {
            if (Build.getSerial().equals("unknown")) {
                nb_of_suspicious_values++;
                suspiciousValues.add("Build.getSerial() = \"unknown\"");
            }
        } else {
            suspiciousValues.add("The permission READ_PHONE_STATE was missing\nCould not find the serial number");
        }

        exampleFragment.setGood(nb_of_suspicious_values<3);
        exampleFragment.addDetails(String.format("There were %d suspicious value(s):", nb_of_suspicious_values), (nb_of_suspicious_values == 0));
        for (String suspiciousValue: suspiciousValues) {
            exampleFragment.addDetails(suspiciousValue , false);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        activity.getFragmentManager().beginTransaction().add(ll.getId(), exampleFragment, TAG).commit();
    }

    private void logValuesBuild() {
        Boolean permission_READ_PHONE_STATE = (ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED);

        Log.d(TAG, "doInBackground: " + Build.BOARD + "\n" //unknown for the emulator google, MHA for my phone
                + Build.BOOTLOADER + "\n"   //unknown for the emulator google, unknown for my phone
                + Build.BRAND + "\n"  // google for the emulator google, HUAWEI for my phone
                + Build.DEVICE + "\n"  //generic_x86 for the emulator google, HWMHA for my phone
                + Build.DISPLAY + "\n" // OSM1.180201.007 fot the emulator google, MHA-L29 8.0.0.368(C636) for my phone
                + Build.FINGERPRINT + "\n"  //google/sdk_gphone_x86/generic_x86:8.1.0/OSM1.180201.007/4586646:user/release-keys for the emulator google, HUAWEI/MHA-L29/HWMHA:8.0.0/HUAWEIMHA-L29/368(C636):user/release-keys for my phone
                + Build.HARDWARE + "\n" // ranchu for the emulator google, hi3660 for my phone
                + Build.HOST + "\n" // abfarm281 for the emulator google, WUH1000129002 for my phone
                + Build.ID + "\n" // OSM1.180201.007 for the emulator google, HUAWEIMHA-L29 for my phone
                + Build.MANUFACTURER + "\n" // Google for the emulator google, HUAWEI for my phone
                + Build.MODEL + "\n" // Android SDK built for x86 for the emulator google, MHA-L29 for my phone
                + Build.PRODUCT + "\n" // sdk_gphone_x86 for the emulator google, MHA-L29 for my phone
                + "type " + Build.TYPE + "\n" // user for the emulator google, user for my phone
                + "user " + Build.USER + "\n" // android-build for the emulator google, test for my phone
                + Build.getRadioVersion() + "\n" // nothing for the emulator google, 21C30B323S001C000,21C30B323S001C000 for my phone
        );

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Log.d(TAG, "doInBackground: " + Arrays.toString(Build.SUPPORTED_32_BIT_ABIS) + "\n" // [x86] for the emulator google, [armeabi-v7a, armeabi] for my phone
                    + Arrays.toString(Build.SUPPORTED_64_BIT_ABIS)); // [] for the emulator google, [arm64-v8a] for my phone);
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) { // unknown for the emulator google, a value for my phone
            Log.d(TAG, "doInBackground: " + Build.SERIAL);
        }else if (permission_READ_PHONE_STATE) {
            Log.d(TAG, "doInBackground: " + Build.getSerial());
        }
    }
}