package com.benjamincastellan.invisible;

import android.widget.LinearLayout;

import com.benjamincastellan.invisible.Tasks.BuildInformation;
import com.benjamincastellan.invisible.Tasks.CheckNbOfAppsInstalled;
import com.benjamincastellan.invisible.Tasks.DebuggerUsed;
import com.benjamincastellan.invisible.Tasks.SimCard;
import com.benjamincastellan.invisible.Tasks.StorageInformation;

import java.util.Date;


public class LaunchTasks {

    public int execute(int nb, MainActivity activity, LinearLayout ll){

        switch (nb) {
            case 0:
                /*
                // it is now not possible to access Chrome history from a third-party app
                CheckInternetHistory checkInternetHistory = new CheckInternetHistory(activity, ll);
                checkInternetHistory.execute();
                */
                return 0;
            case 1:
                CheckNbOfAppsInstalled checkNbOfAppsInstalled = new CheckNbOfAppsInstalled(activity, ll);
                return checkNbOfAppsInstalled.execute();
            case 2:
                StorageInformation storageInformation = new StorageInformation(activity, ll);
                return storageInformation.execute();
            case 3:
                SimCard simCard = new SimCard(activity, ll);
                return simCard.execute();
            case 4:
                BuildInformation buildInformation = new BuildInformation(activity, ll);
                return buildInformation.execute();
            case 5:
                DebuggerUsed debuggerUsed = new DebuggerUsed(activity, ll);
                return debuggerUsed.execute();
            default:
                return 0;
        }
    }
}
