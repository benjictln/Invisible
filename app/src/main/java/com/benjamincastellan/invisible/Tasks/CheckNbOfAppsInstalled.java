package com.benjamincastellan.invisible.Tasks;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.LinearLayout;

import com.benjamincastellan.invisible.ExampleFragment;
import com.benjamincastellan.invisible.MainActivity;

import java.util.List;

import static android.content.ContentValues.TAG;

public class CheckNbOfAppsInstalled extends AsyncTask<Void,Integer,Void> {

    private Activity activity;
    private LinearLayout ll;
    private ExampleFragment exampleFragment;
    private String[] mostFamousApps = {"Facebook", "WhatsApp", "Instagram", "Skype", "Subway Surfers"};

    public CheckNbOfAppsInstalled(MainActivity activity, LinearLayout ll) {
        this.activity = activity;
        this.ll = ll;
        exampleFragment = ExampleFragment.newInstance("I am checking the nb of app installed", true);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values){
        super.onProgressUpdate(values);
        // Mise à jour de la ProgressBar
        Log.d(TAG, "onProgressUpdate: " + String.valueOf(values));


    }

    @Override
    protected Void doInBackground(Void... voids) {
        PackageManager packageManager = activity.getPackageManager();
        // we retrieve the apps installed on the phone
        List<ApplicationInfo> installedApps = activity.getPackageManager().getInstalledApplications(0);
        int numberOfAppsInstalled = installedApps.size();
        Log.d(TAG, "doInBackground: Nb of apps" + String.valueOf(numberOfAppsInstalled ));

        //  we try to see if any of the installed apps match the most installed app on Android
        for (int i = 0; i<numberOfAppsInstalled; i++) {
            String appName = packageManager.getApplicationLabel(installedApps.get(i)).toString();
            Log.d(TAG, String.valueOf(appName));
            int nbTestedOn = 0;
            for (int j = 0; j < mostFamousApps.length; j++){
                if (appName.equals(mostFamousApps[j])) {
                    Log.d(TAG, "doInBackground: WE FOUND A MATCH and it is " + appName);
                }
                else if (++nbTestedOn == mostFamousApps.length){
                    Log.d(TAG, "doInBackground: NO MATCH FOR THIS APP " + appName);
                }
            }
        }
        publishProgress(0);
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        activity.getFragmentManager().beginTransaction().add(ll.getId(), exampleFragment, "someTag1").commit();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
