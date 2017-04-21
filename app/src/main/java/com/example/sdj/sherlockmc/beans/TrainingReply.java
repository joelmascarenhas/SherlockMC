package com.example.sdj.sherlockmc.beans;

/**
 * Created by sdj on 4/16/17.
 */

public class TrainingReply {
    private boolean insertSuccess;


    public boolean isInsertSuccess() {
        return insertSuccess;
    }

    public boolean isTrainingComplete() {
        return trainingComplete;
    }

    private boolean trainingComplete;


    public TrainingReply(boolean insertSuccess, boolean trainingComplete) {
        this.insertSuccess = insertSuccess;
        this.trainingComplete = trainingComplete;
    }
}
