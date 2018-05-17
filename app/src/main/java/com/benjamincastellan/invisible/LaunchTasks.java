package com.benjamincastellan.invisible;

import android.app.Activity;
import android.widget.LinearLayout;

import com.benjamincastellan.invisible.Tasks.CheckInternetHistory;
import com.benjamincastellan.invisible.Tasks.CheckNbOfAppsInstalled;


public class LaunchTasks {

    public void execute(int nb, MainActivity activity, LinearLayout ll){
        switch (nb) {
            case 0:
                CheckInternetHistory checkInternetHistory = new CheckInternetHistory(activity, ll);
                checkInternetHistory.execute();
                break;
            case 1:
                CheckNbOfAppsInstalled checkNbOfAppsInstalled = new CheckNbOfAppsInstalled();
                checkNbOfAppsInstalled.execute();
                break;
            default:
                break;
        }
    }
}
