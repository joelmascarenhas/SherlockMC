package com.example.sdj.sherlockmc.restlayer;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sdj.sherlockmc.beans.TrainData;
import com.example.sdj.sherlockmc.beans.TrainingReply;
import com.example.sdj.sherlockmc.utils.Constants;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by sdj on 4/16/17.
 */

public class UploadTrainingData {
    public static boolean uploadDataToServer(TrainData trainDataObject, Context appContext){
        boolean insertSuccess = true;
        SharedPreferences sharedPreferencesObject = appContext.getSharedPreferences(Constants.LOCAL_VARIABLES,0);
        boolean trainPhase = sharedPreferencesObject.getBoolean(Constants.TRAINING_PHASE_BOOL,true);
        Call<TrainingReply> uploadCall = trainPhase ? RestClient.endPointFetcher().uploadMinuteData(trainDataObject)
                : RestClient.endPointFetcher().uploadTestData(trainDataObject);
        Response<TrainingReply> responseObject;
        TrainingReply respString;
        try {
             responseObject = uploadCall.execute();
             respString = responseObject.body();
            insertSuccess = respString.equals(Constants.FALSE_STRING)?true:false;
            updateSharedPref(appContext,respString.isTrainingComplete());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return insertSuccess;
    }

    private static void updateSharedPref(Context appContext,boolean updateBoolean){
        SharedPreferences sharedPreferencesObject = appContext.getSharedPreferences(Constants.LOCAL_VARIABLES,0);
        sharedPreferencesObject.edit().putBoolean(Constants.TRAINING_PHASE_BOOL,updateBoolean).commit();
    }
}
