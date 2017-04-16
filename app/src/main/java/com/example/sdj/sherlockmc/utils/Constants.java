package com.example.sdj.sherlockmc.utils;

import java.io.File;
import java.io.FileOutputStream;

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
    public static String PHONE_PATH_FOLDER = "/data/data/com.example.sdj.sherlockmc/";
    public static String ACCEL_FILE_NAME = "accel_data.csv";
    public static String SERVICE_MSG_TXT = "InsideServiceClass!";
    public static String EXCEPT_SERVICE_FILE_ACCESS = "ExcepFileOpenService";
    public static String STARTED_SERVICE_MSG = "Service to collect Accelerometer Data Started!";
    public static String SERVICE_TERMINATED_MSG = "Service has been terminated";
    public static String EXCEP_SERVICE_TERMINATED = "ExcepOccTerminServ";
    public static String EXCEP_FILE_WRITE_ACCEL = "ExcepFileWriteAccelData";
    public static String FILE_WRITE_LOG = "AccelWritingToFile";
    public static String RESTART_TRIGGER = "ServiceStartReboot";
    public static String TIMESTAMP_FILE = "firstOpen";
    public static String EXCEP_TS_FILE = "ExcepTstampfileCreation";
    public static String EXCEP_READING_TS_FILE_ALARM_MANAGER = "ExcepAlarmTSFile";
    public static File FILE_OBJECT_LOCKER = new File(Constants.PHONE_PATH_FOLDER+Constants.ACCEL_FILE_NAME);
}
