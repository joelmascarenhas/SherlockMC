package com.example.sdj.sherlockmc.beans;

/**
 * Created by Joel on 17-04-2017.
 */

public class UpdateReply {
    private boolean userUpdation;

    public UpdateReply(boolean userUpdation) {
        this.userUpdation = userUpdation;
    }

    public boolean updateSuccessful() {
        return userUpdation;
    }
}
