package com.example.sdj.sherlockmc.restlayer;

import com.example.sdj.sherlockmc.beans.AuthReply;
import com.example.sdj.sherlockmc.beans.User;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Joel on 17-04-2017.
 */

public class AuthUserLogin {
    public static boolean isValidUser(User user) throws IOException {
        boolean uservalid = false;
        Call<AuthReply> checkLogin = RestClient.endPointFetcher().checkUserLogin(user);
        Response<AuthReply> userStatus;
        AuthReply userAuth;
        userStatus = checkLogin.execute();
        userAuth = userStatus.body();
        return userAuth.isUserPresent();
    }
}
