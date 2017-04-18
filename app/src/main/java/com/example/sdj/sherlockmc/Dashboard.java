package com.example.sdj.sherlockmc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void startAccelRecord(View v){
        Intent recordAccelObject = new Intent(this,DataCollectorAccel.class);
        startService(recordAccelObject);
    }

    public void stopAccelRecord(View v){
        stopService(new Intent(getApplicationContext(),DataCollectorAccel.class));
    }

    public void logoutClicker(View v){
        Intent loginActivity = new Intent(Dashboard.this,LoginActivity.class);
        startActivity(loginActivity);
    }

    public void updateProfile(View v)
    {
        Intent updateUserProfile = new Intent(Dashboard.this,UpdateProfile.class);
        startActivity(updateUserProfile);
    }

}
