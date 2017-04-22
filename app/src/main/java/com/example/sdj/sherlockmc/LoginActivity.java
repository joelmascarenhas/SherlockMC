package com.example.sdj.sherlockmc;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sdj.sherlockmc.beans.User;
import com.example.sdj.sherlockmc.restlayer.AuthUserLogin;
import com.example.sdj.sherlockmc.service.EncryptPassword;
import com.example.sdj.sherlockmc.service.UserEntryToDB;
import com.example.sdj.sherlockmc.utils.Constants;
import com.example.sdj.sherlockmc.utils.DBUtils;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private TextView signup;
    private TextView username;
    private TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        SharedPreferences settings = getSharedPreferences(Constants.LOCAL_VARIABLES,0);
        checkGPSActivated(settings);

        // Initialization
        username = (TextView) findViewById(R.id.login_username);
        password = (TextView) findViewById(R.id.login_password);

        login = (Button) findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str_username = String.valueOf(username.getText());
                String str_password = String.valueOf(password.getText());
                boolean validUser = false;
                User user = null;


                if (str_username.equals("") && str_password.equals("")) {
                    Toast.makeText(getApplicationContext(),"Username and Password is required!",Toast.LENGTH_LONG).show();
                    return;
                }
                else if (str_password.equals("")) {
                    Toast.makeText(getApplicationContext(),"Password is required!",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(str_username.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Username is required!",Toast.LENGTH_LONG).show();
                    return;
                }

                try {
                    user = new User(str_username, EncryptPassword.convertPasswordMD5(str_password),null,null,null);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

                //validUser = true;
                SQLiteDatabase dbCon = openOrCreateDatabase(Constants.PHONE_PATH_FOLDER+Constants.SHERLOCK_DB_NAME_EXTN,MODE_PRIVATE,null);
                DBUtils.createTableUser(Constants.USER_TABLE,dbCon,Constants.CREATE_USER_COLS);
                boolean bool = DBUtils.isTableEmpty(Constants.USER_TABLE,dbCon);
                boolean boolUnique = DBUtils.isEmailUnique(str_username,dbCon);
                if(bool || !boolUnique){
                    UserEntryToDB.insertUserToDB(dbCon,new User(str_username,null,null,null,null));
                }
                dbCon.close();

                try {
                    validUser = AuthUserLogin.isValidUser(user);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d(null,"Login REST call failed");
                    return;
                }
                if(validUser)
                {
                    Log.d(null,"Authentication Successful");
                    Intent intent = new Intent(LoginActivity.this, Dashboard.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(), Constants.INVALID_CREDENTIALS,Toast.LENGTH_LONG).show();

            }
        });

        signup = (TextView) findViewById(R.id.login_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, Register.class);
                startActivity(intent);
            }
        });

    }
    public void checkGPSActivated(SharedPreferences sharedObject){
        if(sharedObject.getBoolean(Constants.GPS_ACTIVATED,true)){
                sharedObject.edit().putBoolean(Constants.GPS_ACTIVATED,true).commit();
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(LoginActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                }
        }
        sharedObject.edit().putBoolean(Constants.GPS_ACTIVATED,false).commit();
    }

    // the database is assumed to be in the path: /data/data/com.example.sdj.sherlockmc/ and the file name should be with extension .db
    public SQLiteDatabase createDBConnection(String dbName,boolean isDefault){
        return isDefault ? openOrCreateDatabase(Constants.PHONE_PATH_FOLDER+Constants.SHERLOCK_DB_NAME_EXTN,MODE_PRIVATE,null) :
                openOrCreateDatabase(Constants.PHONE_PATH_FOLDER+dbName,MODE_PRIVATE,null);

    }
}
