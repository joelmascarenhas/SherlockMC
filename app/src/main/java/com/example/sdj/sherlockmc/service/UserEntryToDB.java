package com.example.sdj.sherlockmc.service;

import android.database.Cursor;
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
        String query = Constants.INSERT_USER_TABLE + "'"+ userObj.getEmail() + "','" +
                userObj.getName() + "','" + userObj.getPrimaryPhone() +"'"+ Constants.QUERY_CLOSING;
        try {
            dbConnection.execSQL(query);
        }catch (Exception e){
            e.printStackTrace();
            Log.d(Constants.ERROR_USER_ENTRY,Constants.ERROR_USER_ENTRY);
        }
    }

    // gives the user object stored in the phone
    public static User getUserInfo(SQLiteDatabase dbConnection){
        User user = null;
        Cursor cursorObject = dbConnection.rawQuery(Constants.SELECT_USER_TABLE,null);
        cursorObject.moveToFirst();
        if(cursorObject.getCount()!=0){
            user = new User(cursorObject.getColumnName(0),null,cursorObject.isNull(1)?null:cursorObject.getColumnName(1),
                    cursorObject.isNull(2)?null:cursorObject.getColumnName(2),null);
        }else{
            Log.d(Constants.NO_USER_DETAILS,Constants.NO_USER_DETAILS);
        }
        return user;
    }
}
