package com.example.sdj.sherlockmc.restlayer;

import com.example.sdj.sherlockmc.beans.UpdateReply;
import com.example.sdj.sherlockmc.beans.User;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Joel on 17-04-2017.
 */

public class UpdateUser
{
    public static boolean updateUser(User user) throws IOException
    {
        Call<UpdateReply> checkUpdation = RestClient.endPointFetcher().updateUser(user);
        Response<UpdateReply>  userUpdateStatus;
        UpdateReply userUpdate;
        userUpdateStatus = checkUpdation.execute();
        userUpdate = userUpdateStatus.body();
        return userUpdate.updateSuccessful();
    }
}
