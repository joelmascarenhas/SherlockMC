package com.example.sdj.sherlockmc.beans;

/**
 * Created by Joel on 17-04-2017.
 */

public class RegisterReply {
    public boolean isRegisterUserProfile() {
        return registerUserProfile;
    }

    private boolean registerUserProfile;

    public RegisterReply(boolean userRegistration) {
        this.registerUserProfile = userRegistration;
    }


}
