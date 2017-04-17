package com.example.sdj.sherlockmc.beans;

/**
 * Created by Joel on 17-04-2017.
 */

public class AuthReply {
    private boolean userPresent;

    public AuthReply(boolean userPresent) {
        this.userPresent = userPresent;
    }

    public boolean isUserPresent() {
        return userPresent;
    }
}
