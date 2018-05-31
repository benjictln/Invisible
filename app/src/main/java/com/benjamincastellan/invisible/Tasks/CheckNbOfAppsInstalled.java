package com.benjamincastellan.invisible.Tasks;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.LinearLayout;

import com.benjamincastellan.invisible.ExampleFragment;
import com.benjamincastellan.invisible.MainActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;

public class CheckNbOfAppsInstalled extends AsyncTask<Void,Integer,Void> {

    private MainActivity activity;
    private LinearLayout ll;
    private ExampleFragment exampleFragment;
    private StringBuilder appMatched;
    final String TAG = "Check the Nb of Apps installed"; // todo: replace it

    // most downloaded according to https://en.wikipedia.org/wiki/List_of_most_downloaded_Google_Play_applications
    private String[] mostFamousApps = {"Facebook", "WhatsApp", "Instagram", "Skype", "Subway Surfers", "Clean Master", "Dropbox", "Candy Crush Saga", "Viber Messenger", "Twitter", "LINE", "HP Print Service Plugin", "Flipboard", "Samsung Print Service Plugin"};

    public CheckNbOfAppsInstalled(MainActivity activity, LinearLayout ll) {
        this.activity = activity;
        this.ll = ll;
        exampleFragment = ExampleFragment.newInstance("Checking the apps installed", true);
        appMatched = new StringBuilder();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //set the start date of the new task
        activity.addStartDate(new Date());
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

        String commentNbOfAppsInstalled;
        if (numberOfAppsInstalled < 100) {
            commentNbOfAppsInstalled = "It is very very low(suspicious)";
        } else if (numberOfAppsInstalled < 120) {
            commentNbOfAppsInstalled = "It is very low(suspicious)";
        } else if (numberOfAppsInstalled < 200) {
            commentNbOfAppsInstalled = "It is low(suspicious)";
        } else if (numberOfAppsInstalled < 300) {
            commentNbOfAppsInstalled = "It is regular";
        } else if (numberOfAppsInstalled < 400) {
            commentNbOfAppsInstalled = "It is high";
        } else {
            commentNbOfAppsInstalled = "It is very high";
        }

        exampleFragment.addDetails("The number of apps installed is " + String.valueOf(numberOfAppsInstalled) + "\n" + commentNbOfAppsInstalled);
        //  we try to see if any of the installed apps match the most installed app on Android
        int nbOfMatches = 0;
        for (int i = 0; i<numberOfAppsInstalled; i++) {
            String appName = packageManager.getApplicationLabel(installedApps.get(i)).toString();
            Log.d(TAG, String.valueOf(appName));
            int nbTestedOn = 0;
            for (int j = 0; j < mostFamousApps.length; j++){
                if (appName.equals(mostFamousApps[j])) {
                    Log.d(TAG, "doInBackground: WE FOUND A MATCH and it is " + appName);
                    nbOfMatches++;
                    appMatched.append("\n - ");
                    appMatched.append(appName);
                }
                else if (++nbTestedOn == mostFamousApps.length){
                    Log.d(TAG, "doInBackground: NO MATCH FOR THIS APP " + appName);
                }
            }
        }
        if ((nbOfMatches == 0) || (nbOfMatches == 1 && numberOfAppsInstalled<200) || (nbOfMatches == 2 && numberOfAppsInstalled<150) || (numberOfAppsInstalled < 110)){
            // it is bad, probably not a real phone
            exampleFragment.setGood(false);
        } else {
            // it is probably a legit phone
            exampleFragment.setGood(true);
        }
        // add comment if it is suspicious
        String commentNbOfMatches = (nbOfMatches==0)? "(Suspicious)" : ":";

        exampleFragment.addDetails("There was " + String.valueOf(nbOfMatches) + " application(s) that matched the most installed on Android" + commentNbOfMatches + appMatched.toString());
        publishProgress(0);
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        activity.getFragmentManager().beginTransaction().add(ll.getId(), exampleFragment, TAG).commit();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
