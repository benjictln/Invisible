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
import java.util.Date;


public class StorageInformation {

    private MainActivity activity;
    private LinearLayout ll;
    private ExampleFragment exampleFragment;
    final String TAG = "Storage Information";

    public StorageInformation(MainActivity activity, LinearLayout ll) {
        this.activity = activity;
        this.ll = ll;
        exampleFragment = ExampleFragment.newInstance("Check the storage of the device", true);
    }

    public int execute() {

        //set the start date of the new task
        activity.addStartDate(new Date());

        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long freeSpace = stat.getFreeBytes();
        long totalSpace = stat.getTotalBytes();
        long usedSpace = totalSpace - freeSpace;
        long oneGigaByte = (long) Math.pow(2,30);
        boolean usedSpaceBad = (usedSpace < 2*oneGigaByte);
        boolean totalSpaceBad = (totalSpace < 3*oneGigaByte);
        exampleFragment.addDetails("Free space: " + convertInAppropriateFormat(freeSpace));
        exampleFragment.addDetails("Used space: " + convertInAppropriateFormat(usedSpace),!usedSpaceBad);
        exampleFragment.addDetails("Total space: " + convertInAppropriateFormat(totalSpace), !totalSpaceBad);

        if (usedSpaceBad || totalSpaceBad)
            exampleFragment.setGood(false);
        else
            exampleFragment.setGood(true);

        activity.getFragmentManager().beginTransaction().add(ll.getId(), exampleFragment, TAG).commit();

        return 1;
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