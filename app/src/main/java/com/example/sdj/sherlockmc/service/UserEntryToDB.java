package com.example.sdj.sherlockmc.service;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sdj.sherlockmc.beans.User;
import com.example.sdj.sherlockmc.utils.Constants;
import com.example.sdj.sherlockmc.utils.DBUtils;

/**
 * Created by sdj on 4/17/17.
 */

public class UserEntryToDB {
    public static void insertUserToDB(SQLiteDatabase dbConnection,User userObj){
        if(!DBUtils.isTableExists(Constants.USER_TABLE,dbConnection)){
            DBUtils.createTable(Constants.CREATE_USER_TABLE,dbConnection);
        }
        String query = Constants.INSERT_USER_TABLE + userObj.getEmail() + "," +
                userObj.getName() + "," + userObj.getPrimaryPhone() + Constants.QUERY_CLOSING;
        try {
            dbConnection.execSQL(query);
        }catch (Exception e){
            e.printStackTrace();
            Log.d(Constants.ERROR_USER_ENTRY,Constants.ERROR_USER_ENTRY);
        }
    }
}
