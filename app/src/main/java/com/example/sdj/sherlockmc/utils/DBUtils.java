package com.example.sdj.sherlockmc.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by sdj on 4/17/17.
 */

public class DBUtils {
    public static boolean isTableExists(String tableName, SQLiteDatabase dbConnectionObject) {
        Cursor cursor = dbConnectionObject.rawQuery(Constants.CHECKING_TABLE_QUERY+tableName+Constants.SINGLE_QUOTE, null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }

    public static void createTable(String tableName, SQLiteDatabase dbConnectionObject)
    {
        Log.d(Constants.TABLE_NAME_TEXT,tableName);
        if(DBUtils.isTableExists(tableName,dbConnectionObject)){
            Log.d(Constants.TABLE_EXISTS_TEXT,tableName);
            return;
        }
        try {
            dbConnectionObject.execSQL(Constants.QUERY_CREATE_TABLE_FIRST + tableName + Constants.TABLE_CREATE_COLUMN_TEXT);
            Log.d(Constants.TABLE_CREATED_TEXT, tableName);
        }
        catch (Exception e)
        {
            Log.d(Constants.TAB_EXISTS,Constants.TAB_EXISTS);
            e.printStackTrace();
        }
    }

}
