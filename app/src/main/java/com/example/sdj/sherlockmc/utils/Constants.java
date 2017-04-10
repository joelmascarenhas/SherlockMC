package com.example.sdj.sherlockmc.utils;

/**
 * Created by sdj on 4/9/17.
 */

public class Constants {
    public static String ACCEL_TABLE_NAME = "training_log_accel";
    public static String INSERT_QUERY_PART_ONE = "INSERT INTO ";
    public static String INSERT_QUERY_VALUES_PART =  " VALUES (";
    public static String QUERY_CLOSING = ");";
    public static String EXCEPTION_INSERT_INTENT_SERVICE = "Exception occured in backgroud service" +
            "while trying to insert data into the DB";
    public static String REGISTERED_LISTENER = "Listener registered";
    public static String TABLE_NAME_TEXT = "TableNameCreated";
    public static String QUERY_CREATE_TABLE_FIRST = "CREATE TABLE ";
    public static String TABLE_CREATED_TEXT = "Table Created!";
    public static String TABLE_CREATE_COLUMN_TEXT = " (Time_Stamp REAL, X_Value REAL, Y_Value REAL, Z_Value REAL);";
    public static String TABLE_EXISTS_TEXT = "Table Already Exists!";
    public static String SENSOR_ACCURACY_CHANGE_TEXT = "SensorAccuracyChanged!";
    public static String SHERLOCK_DB_NAME = "sherlockdb";
}
