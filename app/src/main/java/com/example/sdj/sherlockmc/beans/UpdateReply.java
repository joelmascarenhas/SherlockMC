package com.example.sdj.sherlockmc.beans;

/**
 * Created by Joel on 17-04-2017.
 */

public class UpdateReply {
    private boolean updateUserProfile;

    public UpdateReply(boolean updateUserProfile) {
        this.updateUserProfile = updateUserProfile;
    }

    public boolean updateSuccessful() {
        return updateUserProfile;
    }
}
