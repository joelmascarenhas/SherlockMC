package com.example.sdj.sherlockmc;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sdj.sherlockmc.beans.UpdateReply;
import com.example.sdj.sherlockmc.beans.User;
import com.example.sdj.sherlockmc.restlayer.AuthUserLogin;
import com.example.sdj.sherlockmc.restlayer.RegisterUser;
import com.example.sdj.sherlockmc.restlayer.UpdateUser;
import com.example.sdj.sherlockmc.service.EncryptPassword;
import com.example.sdj.sherlockmc.service.UserEntryToDB;
import com.example.sdj.sherlockmc.utils.Constants;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class UpdateProfile extends AppCompatActivity {

    private Button update;
    private EditText oldpassword;
    private EditText newpassword;
    private EditText primaryPhone;
    private EditText emergencyPhone;
    private CheckBox passwordCheckbox;
    private CheckBox primaryphoneCheckbox;
    private CheckBox emergencyphoneCheckbox;
    private SQLiteDatabase dbcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        oldpassword = (EditText) findViewById(R.id.update_oldpassword);
        newpassword = (EditText) findViewById(R.id.update_newpassword);
        primaryPhone = (EditText) findViewById(R.id.update_primaryphone);
        emergencyPhone = (EditText) findViewById(R.id.update_emergencyPhone);
        passwordCheckbox = (CheckBox) findViewById(R.id.checkBox_password);
        primaryphoneCheckbox = (CheckBox) findViewById(R.id.checkBox_primaryphone);
        emergencyphoneCheckbox = (CheckBox) findViewById(R.id.checkBox_emergencyphone);
        update = (Button) findViewById(R.id.update_button);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_oldpassword = String.valueOf(oldpassword.getText());
                String str_newpassword = String.valueOf(newpassword.getText());
                String str_primaryphone = String.valueOf(primaryPhone.getText());
                String str_emergencyphone = String.valueOf(emergencyPhone.getText());
                User checkOldPwdUser = null;
                User updateUserProfile = null;
                boolean updateSuccess = false;
                boolean oldpasswordvalid = false;
                dbcon = openOrCreateDatabase(Constants.PHONE_PATH_FOLDER+Constants.SHERLOCK_DB_NAME_EXTN,MODE_PRIVATE,null);
                String email = UserEntryToDB.getUserInfo(dbcon).getEmail();
                String password = null;
                String primaryphone = null;
                String emergencyphone = null;

                if(passwordCheckbox.isChecked())
                {
                    if (str_oldpassword.equals("") || str_newpassword.equals("")) {
                        Toast.makeText(getApplicationContext(),"Old Password and New Password cannot be blank",Toast.LENGTH_LONG).show();
                        return;
                    }
                    else if(str_oldpassword.equals(""))
                    {
                        Toast.makeText(getApplicationContext(),Constants.BLANK_OLD_PASSWORD,Toast.LENGTH_LONG).show();
                        return;
                    }
                    else if(str_newpassword.equals(""))
                    {
                        Toast.makeText(getApplicationContext(),Constants.BLANK_NEW_PASSWORD,Toast.LENGTH_LONG).show();
                        return;
                    }
                    else
                    {
                        try {
                            checkOldPwdUser = new User(email, EncryptPassword.convertPasswordMD5(str_oldpassword),null,null,null);
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                            Log.d(null,"MD5 conversion failed");
                            return;
                        }

                        try {
                            oldpasswordvalid = AuthUserLogin.isValidUser(checkOldPwdUser);
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.d(null,"Login REST call failed");
                            return;
                        }

                        if(oldpasswordvalid) {
                            try {
                                password = EncryptPassword.convertPasswordMD5(str_newpassword);
                            } catch (NoSuchAlgorithmException e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Old Password does not match !!", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                }
                if(primaryphoneCheckbox.isChecked())
                {
                    if (str_primaryphone.equals("")) {
                        Toast.makeText(getApplicationContext(), Constants.BLANK_PRIMARY, Toast.LENGTH_LONG).show();
                        return;
                    }
                    else
                        primaryphone = str_primaryphone;
                }
                if(emergencyphoneCheckbox.isChecked())
                {
                    if (str_emergencyphone.equals("")) {
                        Toast.makeText(getApplicationContext(),Constants.BLANK_EMERGENCY,Toast.LENGTH_LONG).show();
                        return;
                    }
                    else
                        emergencyphone = str_emergencyphone;
                }
                updateUserProfile = new User(email,password,null,primaryphone,emergencyphone);
                try {
                    updateSuccess = UpdateUser.updateUser(updateUserProfile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(updateSuccess)
                {
                    Toast.makeText(getApplicationContext(),"Update Successful",Toast.LENGTH_LONG).show();
                    Intent Dashboard = new Intent(UpdateProfile.this,Dashboard.class);
                    startActivity(Dashboard);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Update Failed, Try again",Toast.LENGTH_LONG).show();
                    return;
                }

            }

        });
    }
}
