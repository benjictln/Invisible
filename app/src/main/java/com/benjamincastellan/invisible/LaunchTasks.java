package com.benjamincastellan.invisible;

import android.widget.LinearLayout;

import com.benjamincastellan.invisible.Tasks.BuildInformation;
import com.benjamincastellan.invisible.Tasks.CheckNbOfAppsInstalled;
import com.benjamincastellan.invisible.Tasks.SimCard;
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
            case 3:
                SimCard simCard = new SimCard(activity, ll);
                simCard.execute();
                break;
            case 4:
                BuildInformation buildInformation = new BuildInformation(activity, ll);
                buildInformation.execute();
                break;
            default:
                break;
        }
    }
}
