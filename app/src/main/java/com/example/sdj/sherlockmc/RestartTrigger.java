package com.example.sdj.sherlockmc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.sdj.sherlockmc.utils.Constants;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by sdj on 4/10/17.
 */

public class RestartTrigger extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceLauncher = new Intent(context, DataCollectorAccel.class);
        context.startService(serviceLauncher);
        // check if it is a week since install
        if(checkTSOneWeek()){
            // if so start sending five minutes data to server

        }
        Log.d(Constants.RESTART_TRIGGER,Constants.RESTART_TRIGGER);
    }

    // function to check if it has been a week since app is installed
    // we send data in bulk after a week
    // After a week we receive an acknowledgement from server that training is done
    // Once we receive acknowledgment we keep sending data every five minutes,
    // test the data and trigger alarm if there is an anomaly
    public boolean checkTSOneWeek(){
        BufferedReader inObj = null;
        String readString = null;
        double timeStampSinceInstall = 0;
        try {
            inObj = new BufferedReader(new FileReader(Constants.PHONE_PATH_FOLDER+Constants.TIMESTAMP_FILE));
            while((readString = inObj.readLine()) !=null){
                timeStampSinceInstall = Double.parseDouble(readString);
            }
            inObj.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
            Log.e(Constants.EXCEP_READING_TS_FILE_ALARM_MANAGER,Constants.EXCEP_READING_TS_FILE_ALARM_MANAGER);
        }catch (IOException e){
            e.printStackTrace();
            Log.e(Constants.EXCEP_READING_TS_FILE_ALARM_MANAGER,Constants.EXCEP_READING_TS_FILE_ALARM_MANAGER);
        }
        return System.currentTimeMillis()-timeStampSinceInstall > 604800000;
    }
}
