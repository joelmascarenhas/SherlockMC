package com.example.sdj.sherlockmc.beans;

/**
 * Created by Joel on 17-04-2017.
 */

public class RegisterReply {
    private boolean userRegistration;

    public RegisterReply(boolean userRegistration) {
        this.userRegistration = userRegistration;
    }

    public boolean registerSuccessful() {
        return userRegistration;
    }
}
