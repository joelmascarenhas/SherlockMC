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
        Call<TrainingReply> uploadCall = RestClient.endPointFetcher().uploadMinuteData(trainDataObject);
        Response<TrainingReply> responseObject;
        TrainingReply respString;
        try {
            responseObject = uploadCall.execute();
            respString = responseObject.body();
            insertSuccess = respString.isInsertSuccess()?true:false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return insertSuccess;
    }
}