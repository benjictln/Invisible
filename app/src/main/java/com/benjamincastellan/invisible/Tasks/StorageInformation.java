package com.benjamincastellan.invisible.Tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import android.widget.LinearLayout;

import com.benjamincastellan.invisible.ExampleFragment;
import com.benjamincastellan.invisible.MainActivity;

import java.text.DecimalFormat;


public class StorageInformation extends AsyncTask<Void,Integer,Void> {

    private Activity activity;
    private LinearLayout ll;
    private ExampleFragment exampleFragment;
    final String TAG = "Storage Information";

    public StorageInformation(MainActivity activity, LinearLayout ll) {
        this.activity = activity;
        this.ll = ll;
        exampleFragment = ExampleFragment.newInstance("Check the storage of the device", true);
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
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long freeSpace = stat.getFreeBytes();
        long totalSpace = stat.getTotalBytes();
        long usedSpace = totalSpace - freeSpace;
        exampleFragment.addDetails("Free space: " + convertInAppropriateFormat(freeSpace));
        exampleFragment.addDetails("Used space: " + convertInAppropriateFormat(usedSpace));
        exampleFragment.addDetails("Total space: " + convertInAppropriateFormat(totalSpace));

        long oneGigaByte = (long) Math.pow(2,30);
        if (usedSpace < 2*oneGigaByte || totalSpace < 3*oneGigaByte)
            exampleFragment.setGood(false);
        else
            exampleFragment.setGood(true);
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        activity.getFragmentManager().beginTransaction().add(ll.getId(), exampleFragment, TAG).commit();
    }


    public String convertInAppropriateFormat(long value) {
        // convert in GigaBytes, KiloBytes or MegaBytes according to the most appropriate
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        if (value > Math.pow(2,30)) {
            return (String.valueOf(numberFormat.format(value/(Math.pow(2,30)))) + " Gb");
        }
        if (value > Math.pow(2,20)) {
            return (String.valueOf(numberFormat.format(value/(Math.pow(2,20)))) + " Mb");
        }
        return (String.valueOf(numberFormat.format(value/(Math.pow(2,10)))) + " Kb");
    }
}