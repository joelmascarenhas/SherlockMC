package com.example.sdj.sherlockmc.restlayer;

import com.example.sdj.sherlockmc.beans.AuthReply;
import com.example.sdj.sherlockmc.beans.RegisterReply;
import com.example.sdj.sherlockmc.beans.TrainData;
import com.example.sdj.sherlockmc.beans.TrainingReply;
import com.example.sdj.sherlockmc.beans.UpdateReply;
import com.example.sdj.sherlockmc.beans.User;
import com.example.sdj.sherlockmc.utils.Constants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

/**
 * Created by sdj on 4/16/17.
 *
 * Add whatever API endpoints that we need to consume using REST call here
 */

public interface APIEndpoints {
    @POST(Constants.UPLOAD_DATA_REST)
    Call<TrainingReply> uploadMinuteData(@Body TrainData trainDataObject);

    @POST(Constants.UPLOAD_DATA_TEST)
    Call<TrainingReply> uploadTestData(@Body TrainData trainDataObject);

    @POST(Constants.CHECK_USER_AUTH)
    Call<AuthReply> checkUserLogin(@Body User user);

    @Headers("Content-Type: application/json")
    @POST(Constants.REGISTER_USER)
    Call<RegisterReply> registerUser(@Body User user);

    @PATCH(Constants.UPDATE_USER)
    Call<UpdateReply> updateUser(@Body User user);
}
