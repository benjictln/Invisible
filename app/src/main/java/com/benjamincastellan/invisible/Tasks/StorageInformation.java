package com.benjamincastellan.invisible.Tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import android.widget.LinearLayout;

import com.benjamincastellan.invisible.ExampleFragment;
import com.benjamincastellan.invisible.MainActivity;


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
        exampleFragment.addDetails("free space + " + convertInAppropriateFormat(freeSpace));
        long totalSpace = stat.getTotalBytes();
        exampleFragment.addDetails("Total space + " + convertInAppropriateFormat(totalSpace));
        long megAvailable = bytesAvailable / 1048576;
        Log.d(TAG,"Megs :"+megAvailable);
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        activity.getFragmentManager().beginTransaction().add(ll.getId(), exampleFragment, TAG).commit();
    }


    public String convertInAppropriateFormat(long value) {
        // convert in GigaBytes, KiloBytes or MegaBytes according to the most appropriate
        if (value > Math.pow(2,30)) {
            return (String.valueOf(value/(Math.pow(2,30))) + " Gb");
        }
        if (value > Math.pow(2,20)) {
            return (String.valueOf(value/(Math.pow(2,20))) + " Mb");
        }
        return (String.valueOf(value/(Math.pow(2,10))) + " Kb");
    }
}