package com.example.sdj.sherlockmc.restlayer;
import com.example.sdj.sherlockmc.Register;
import com.example.sdj.sherlockmc.beans.AuthReply;
import com.example.sdj.sherlockmc.beans.RegisterReply;
import com.example.sdj.sherlockmc.beans.User;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Joel on 17-04-2017.
 */

public class RegisterUser {
    public static boolean registerUser(User user) throws IOException {
        boolean uservalid = false;
        Call<RegisterReply> checkRegistration = RestClient.endPointFetcher().registerUser(user);
        Response<RegisterReply> userRegisterStatus;
        RegisterReply userRegister;
        userRegisterStatus = checkRegistration.execute();
        userRegister = userRegisterStatus.body();
        return userRegister.registerSuccessful();
    }
}

