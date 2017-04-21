package com.example.sdj.sherlockmc;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.sdj.sherlockmc.restlayer.UploadTrainingData;
import com.example.sdj.sherlockmc.service.UploadService;
import com.example.sdj.sherlockmc.utils.Constants;
import com.example.sdj.sherlockmc.utils.DBUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/*
* Reference: https://github.com/nicolashahn/BackgroundAccelerometer
*
* */
public class DataCollectorAccel extends Service {
    private SensorManager sensorManagerObject;
    private Sensor accelSensorObject;
    private long previousTime=System.currentTimeMillis();
    private SQLiteDatabase dbConnectionObject;
    private FileOutputStream outputStreamObject;
    private FileWriter writerObject;
    private boolean initBool;
    private long sendingPulse = System.currentTimeMillis();

    private SensorEventListener acclListener=new SensorEventListener() {

        // On Sensor Change every second writing into the file
        @Override
        public void onSensorChanged(SensorEvent acclEvent) {
            android.os.Debug.waitForDebugger();
            Sensor accelSensorObject = acclEvent.sensor;
            String msg;
            float x,y,z;
            long currentTime;
            if(!initBool)
                initBool = true;
            if (accelSensorObject.getType() == Sensor.TYPE_ACCELEROMETER) {
                x = acclEvent.values[0];
                y = acclEvent.values[1];
                z = acclEvent.values[2];
                currentTime = System.currentTimeMillis();
                msg=Long.toString(currentTime)+","+Float.toString(x)+","+Float.toString(y)+","+Float.toString(z);
                if ((currentTime-previousTime)>Constants.ONE_SEC_MILLIS) {
                    writeDataToDB(msg);
                    previousTime=currentTime;
                }
                // Try thrice if the REST call fails
                if((currentTime-sendingPulse)>Constants.FIVE_MINUTE_MILLIS_MORE){
                    sendingPulse = currentTime;
                    if(UploadTrainingData.uploadDataToServer(UploadService.fetchFirstThreeHundread(dbConnectionObject,getApplicationContext()),getApplicationContext())) {
                        Log.d("Insert Successfull","Insert successfull");
                    }
                        else{
                        Log.d(Constants.UPLOAD_FAILED_REST,Constants.UPLOAD_FAILED_REST);
                    }
                }
            }
        }

        private void writeDataToDB(String msg){
            String query = Constants.INSERT_QUERY_PART_ONE + Constants.ACCEL_TABLE_NAME +
                    Constants.INSERT_QUERY_VALUES_PART + msg + Constants.QUERY_CLOSING;
            try{
                dbConnectionObject.execSQL(query);
                Log.d(Constants.TRAIN_FLAG_MSG,Constants.TRAIN_FLAG_MSG);
            }catch (Exception e){
                e.printStackTrace();
                if(dbConnectionObject.isOpen())
                    dbConnectionObject.close();
                Log.d(Constants.EXCEP_INSERT_TRAIN_DATA,Constants.EXCEP_INSERT_TRAIN_DATA);
            }
        }
        // Function to write into the file
        public void writeAccelData(String msg){
            try
            {
                writerObject.write(msg);
                writerObject.flush();
                Log.d(Constants.FILE_WRITE_LOG,Constants.FILE_WRITE_LOG);
            }catch (Exception e){
                e.printStackTrace();
                if(dbConnectionObject.isOpen())
                    dbConnectionObject.close();
                Log.e(Constants.EXCEP_FILE_WRITE_ACCEL,Constants.EXCEP_FILE_WRITE_ACCEL);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            Log.d(Constants.SENSOR_ACCURACY_CHANGE_TEXT,Constants.SENSOR_ACCURACY_CHANGE_TEXT);
        }
    };




    // Register Sensor to start recording data
    private void registerAcclListener()
    {
        sensorManagerObject = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelSensorObject = sensorManagerObject.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManagerObject.registerListener(acclListener,accelSensorObject,SensorManager.SENSOR_DELAY_NORMAL);
        Log.d(Constants.REGISTERED_LISTENER,Constants.REGISTERED_LISTENER);
    }

    // UnRegistering sensor
    public void unregisterListener(){
        sensorManagerObject.unregisterListener(acclListener);
    }

    public DataCollectorAccel() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SharedPreferences settings = getSharedPreferences(Constants.LOCAL_VARIABLES,0);
        checkOpenedFirstTime(settings);
        Log.d(Constants.SERVICE_MSG_TXT,Constants.SERVICE_MSG_TXT);
        dbConnectionObject = openOrCreateDatabase(Constants.PHONE_PATH_FOLDER+Constants.SHERLOCK_DB_NAME_EXTN,MODE_PRIVATE,null);
        DBUtils.createTable(Constants.ACCEL_TABLE_NAME,dbConnectionObject);
        try{
            synchronized (Constants.FILE_OBJECT_LOCKER){
                outputStreamObject = new FileOutputStream(Constants.PHONE_PATH_FOLDER+Constants.ACCEL_FILE_NAME,true);
                writerObject = new FileWriter(outputStreamObject.getFD());
            }
        }catch (Exception e){
            e.printStackTrace();
            if(dbConnectionObject.isOpen())
                dbConnectionObject.close();
            Log.d(Constants.EXCEPT_SERVICE_FILE_ACCESS,Constants.EXCEPT_SERVICE_FILE_ACCESS);
        }
        Toast.makeText(this,Constants.STARTED_SERVICE_MSG,Toast.LENGTH_LONG).show();
        initBool = false;
        registerAcclListener();
        registerTimeStamp();
        return START_STICKY;
    }

    // Check if opened for first time and save timestamp
    public void checkOpenedFirstTime(SharedPreferences sharedObject){
        if(sharedObject.getBoolean(Constants.FIRST_OPEN_STRING,true)){
            sharedObject.edit().putLong(Constants.TIMESTAMP_FIRST_TIME,System.currentTimeMillis()).commit();
            sharedObject.edit().putBoolean(Constants.TRAINING_PHASE_BOOL,true).commit();
        }
        sharedObject.edit().putBoolean(Constants.FIRST_OPEN_STRING,false).commit();
    }

    public void registerTimeStamp(){
        File fileObject = new File(Constants.PHONE_PATH_FOLDER+Constants.TIMESTAMP_FILE);
        FileOutputStream tsOutputObject = null;
        FileWriter tsWriterObject = null;
        if(!fileObject.exists()){
            try {
                fileObject.createNewFile();
                tsOutputObject = new FileOutputStream(fileObject,false);
                tsWriterObject = new FileWriter(tsOutputObject.getFD());
                tsWriterObject.write(Long.toString(System.currentTimeMillis()));
                tsWriterObject.flush();
                tsWriterObject.close();
                tsOutputObject.getFD().sync();
                tsOutputObject.close();
            }catch (IOException e){
                e.printStackTrace();
                Log.d(Constants.EXCEP_TS_FILE,Constants.EXCEP_TS_FILE);
            }
        }
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Toast.makeText(this,Constants.SERVICE_TERMINATED_MSG,Toast.LENGTH_LONG).show();
        unregisterListener();
        try
        {
            writerObject.close();
            outputStreamObject.getFD().sync();
            outputStreamObject.close();
        }catch (Exception e){
            e.printStackTrace();
            if(dbConnectionObject.isOpen())
                dbConnectionObject.close();
            Log.e(Constants.EXCEP_SERVICE_TERMINATED,Constants.EXCEP_SERVICE_TERMINATED);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
