package com.example.sdj.sherlockmc;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

import com.example.sdj.sherlockmc.utils.Constants;

/*
* Reference: https://github.com/nicolashahn/BackgroundAccelerometer
*
* */
public class DataCollectorAccel extends Service {
    private SensorManager sensorManagerObject;
    private Sensor accelSensorObject;
    private long previousTime=0;
    private SQLiteDatabase dbConnectionObject;

    private SensorEventListener acclListener=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent acclEvent) {
            Sensor accelSensorObject = acclEvent.sensor;
            String msg;
            float x,y,z;
            long currentTime;
            if (accelSensorObject.getType() == Sensor.TYPE_ACCELEROMETER) {
                x = acclEvent.values[0];
                y = acclEvent.values[1];
                z = acclEvent.values[2];
                currentTime = System.currentTimeMillis();
                msg=Long.toString(currentTime)+","+Float.toString(x)+","+Float.toString(y)+","+Float.toString(z);

                if ((currentTime-previousTime)>1000) {
                    try {
                        dbConnectionObject.execSQL(Constants.INSERT_QUERY_PART_ONE + Constants.ACCEL_TABLE_NAME +
                                Constants.INSERT_QUERY_VALUES_PART + msg + Constants.QUERY_CLOSING);
                    }
                    catch (Exception e)
                    {
                        Log.d(e.getMessage(),Constants.EXCEPTION_INSERT_INTENT_SERVICE);
                    }
                    previousTime=currentTime;
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            Log.d(Constants.SENSOR_ACCURACY_CHANGE_TEXT,Constants.SENSOR_ACCURACY_CHANGE_TEXT);
        }
    };

    private void createTable(String tableName,SQLiteDatabase connection)
    {
        Log.d(Constants.TABLE_NAME_TEXT,tableName);
        try {
            connection.execSQL(Constants.QUERY_CREATE_TABLE_FIRST + tableName + Constants.TABLE_CREATE_COLUMN_TEXT);
            Log.d(Constants.TABLE_CREATED_TEXT, tableName);
        }
        catch (Exception e)
        {
            Log.d(Constants.TABLE_EXISTS_TEXT,tableName);
        }
    }

    // Register Sensor to start recording data
    private void registerAcclListener()
    {
        sensorManagerObject = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelSensorObject = sensorManagerObject.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManagerObject.registerListener(acclListener,accelSensorObject,SensorManager.SENSOR_DELAY_NORMAL);
        Log.d(Constants.REGISTERED_LISTENER,Constants.REGISTERED_LISTENER);
    }

    public DataCollectorAccel() {
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
