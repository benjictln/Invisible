package com.benjamincastellan.invisible;

import android.app.Activity;
import android.widget.LinearLayout;

import com.benjamincastellan.invisible.Tasks.CheckInternetHistory;
import com.benjamincastellan.invisible.Tasks.CheckNbOfAppsInstalled;
import com.benjamincastellan.invisible.Tasks.StorageInformation;


public class LaunchTasks {

    public void execute(int nb, MainActivity activity, LinearLayout ll){
        switch (nb) {
            case 0:
                /*
                // it is now not possible to access Chrome history from a third-party app
                CheckInternetHistory checkInternetHistory = new CheckInternetHistory(activity, ll);
                checkInternetHistory.execute();
                */
                break;
            case 1:
                CheckNbOfAppsInstalled checkNbOfAppsInstalled = new CheckNbOfAppsInstalled(activity, ll);
                checkNbOfAppsInstalled.execute();
                break;
            case 2:
                StorageInformation storageInformation = new StorageInformation(activity, ll);
                storageInformation.execute();
                break;
            default:
                break;
        }
    }
}
