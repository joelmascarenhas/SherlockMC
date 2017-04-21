package com.example.sdj.sherlockmc.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sdj.sherlockmc.beans.AtomData;
import com.example.sdj.sherlockmc.beans.GPSData;
import com.example.sdj.sherlockmc.beans.TrainData;
import com.example.sdj.sherlockmc.utils.Constants;
import com.example.sdj.sherlockmc.utils.DBUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sdj on 4/16/17.
 */

public class UploadService {
    public static TrainData fetchFirstThreeHundread(SQLiteDatabase dbConnectionObject, Context appContext){
        TrainData trainObject = null;
        Cursor cursorObject = dbConnectionObject.rawQuery(Constants.SELECT_EMAIL,null);
        cursorObject.moveToFirst();
        String emailId = cursorObject.getString(0);
        GPSData gpsObject = new GPSService(appContext).getLocation();
        trainObject = new TrainData(emailId,getTopFromDB(dbConnectionObject),gpsObject);
        return trainObject;
    }


    public static List<AtomData> getTopFromDB(SQLiteDatabase dbConnection){
        List<AtomData> listData = new ArrayList<>();
        AtomData atom = null;
        Cursor cursorObject = dbConnection.rawQuery(Constants.SELECT_TRAIN_DATA,null);
        cursorObject.moveToFirst();
        if(cursorObject.getCount() == 0){
            return null;
        }
        do {
            atom = new AtomData(cursorObject.getLong(0),cursorObject.getDouble(1),
                    cursorObject.getDouble(2),cursorObject.getDouble(3));
            listData.add(atom);
        }while (cursorObject.moveToNext());
        // deleting top 300 datapoints
        dbConnection.execSQL(Constants.DELETE_TOP_DATAPOINT);
        return listData;
    }

}
