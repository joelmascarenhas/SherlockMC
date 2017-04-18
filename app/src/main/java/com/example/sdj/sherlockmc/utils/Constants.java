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
    public static String SHERLOCK_DB_NAME_EXTN = "sherlockdb.db";
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
    public static String DB_PATH = "databases/";
    public static String CHECKING_TABLE_QUERY = "select DISTINCT tbl_name from sqlite_master where tbl_name = '";
    public static String SINGLE_QUOTE = "'";
    public static String TAB_EXISTS= "TableExistsCReatTabServ";
    public static String TRAIN_FLAG_MSG = "InsertingTrainingData";
    public static String EXCEP_INSERT_TRAIN_DATA = "ExcpInsertDataAcclData";
    public static int ONE_SEC_MILLIS = 1000;
    public static int FIVE_MINUTE_MILLIS_MORE = 310000;
    public static String EXCEP_UPLOAD_REST_CALL = "ExcepRESTAPIUpload";
    public static final String TRUE_STRING = "true";
    public static final String FALSE_STRING = "false";
    public static final String INVALID_CREDENTIALS = "Invalid Username or Password";
    public static final String REGISTRATION_SUCCESSFUL = "User Registration successful. Please Login !!";
    public static final String EMAIL_EXISTS = "User Registration failed. Email already exists !!";
    public static final String BLANK_PRIMARY = "Primary phone cannot be blank";
    public static final String BLANK_EMERGENCY = "Emergency phone cannot be blank";
    public static final String BLANK_OLD_PASSWORD = "Old Password cannot be blank!";
    public static final String BLANK_NEW_PASSWORD = "New Password cannot be blank!";


    // REST End Point Formats
    public static String BASE_URL = "";
    // end point url for the data insertion into the server
    // this should return a string value as True or False on successful insertion
    public static final String UPLOAD_DATA_REST = "/datatraininginsert";
    public static final String UPLOAD_DATA_TEST = "/testthisdata";
    public static final String CHECK_USER_AUTH = "/userauthlogin";
    public static final String REGISTER_USER = "/registeruserprofile";
    public static final String UPDATE_USER = "/updateuserprofile";
    public static File FILE_OBJECT_LOCKER = new File(Constants.PHONE_PATH_FOLDER+Constants.ACCEL_FILE_NAME);


    // Table Names
    public static final String USER_TABLE = "user_detail";

    // Queries
    public static final String SELECT_EMAIL = "select email from " + Constants.USER_TABLE + " limit 1;";
    public static final String SELECT_TRAIN_DATA = "select * from "+Constants.ACCEL_TABLE_NAME + " order by Time_Stamp limit 300";
    public static final String DELETE_TOP_DATAPOINT = "delete from " + Constants.ACCEL_TABLE_NAME +
            " where Time_Stamp in (select Time_Stamp from "+Constants.ACCEL_TABLE_NAME+
            " order by Time_Stamp limit 300);";
    public static final String CREATE_USER_TABLE = "create table "+Constants.USER_TABLE + " (email text,name text, phone text);";
    public static final String INSERT_USER_TABLE = Constants.INSERT_QUERY_PART_ONE + Constants.USER_TABLE
            +Constants.INSERT_QUERY_VALUES_PART ;
    public static final String SELECT_USER_TABLE = "select * from "+ Constants.USER_TABLE + ";";

    // Exception Messages
    public static final String UPLOAD_FAILED_REST = "uploadrestfailedtrial:";
    public static final String PERMISSION_DENIED_GPS = "PermDeniedGPS";
    public static final String PASSIVE_POINT = "Passive GPS Location";
    public static final String LOG_CITY_NAME = "CityName";
    public static final String ERROR_GPS = "ExcepGPS";
    public static final String ERROR_USER_ENTRY = "ExcepUserEntryDB";
    public static final String NO_USER_DETAILS = "nouserdetailslogged";

    // Preference files
    public static final String LOCAL_VARIABLES = "info";
    public static final String FIRST_OPEN_STRING = "firstOpen";
    public static final String TIMESTAMP_FIRST_TIME = "timeStampFirst";
    public static final String TRAINING_PHASE_BOOL = "trainingPhase";
    public static final String GPS_ACTIVATED = "gpsactivated";
}
