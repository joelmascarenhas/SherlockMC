package com.example.sdj.sherlockmc;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sdj.sherlockmc.beans.User;
import com.example.sdj.sherlockmc.restlayer.RegisterUser;
import com.example.sdj.sherlockmc.service.EncryptPassword;
import com.example.sdj.sherlockmc.service.UserEntryToDB;
import com.example.sdj.sherlockmc.utils.Constants;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Register extends AppCompatActivity {

    private Button register;
    private EditText name;
    private EditText email;
    private EditText password;
    private EditText primaryPhone;
    private EditText emergencyPhone;
    private SQLiteDatabase dbcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        name = (EditText) findViewById(R.id.register_name);
        email = (EditText) findViewById(R.id.register_emailId);
        password = (EditText) findViewById(R.id.register_password);
        primaryPhone = (EditText) findViewById(R.id.register_primaryPhone);
        emergencyPhone = (EditText) findViewById(R.id.register_emergencyPhone);
        register = (Button) findViewById(R.id.register_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String str_name = String.valueOf(name.getText());
                String str_password = String.valueOf(password.getText());
                String str_email = String.valueOf(email.getText());
                String str_primaryphone = String.valueOf(primaryPhone.getText());
                String str_emergencyphone = String.valueOf(emergencyPhone.getText());
                String email_validate = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                User user = null;
                dbcon = openOrCreateDatabase(Constants.PHONE_PATH_FOLDER+Constants.SHERLOCK_DB_NAME_EXTN,MODE_PRIVATE,null);
                boolean registerSuccess = false;

                if (str_name.equals("") || str_password.equals("") || str_email.equals("") || str_primaryphone.equals("") || str_emergencyphone.equals("")) {
                    Toast.makeText(getApplicationContext(),"All fields are mandatory!",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(!str_email.matches(email_validate))
                {
                    Toast.makeText(getApplicationContext(),"Email Id is invalid!",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(str_primaryphone.length() < 10 || !android.util.Patterns.PHONE.matcher(str_primaryphone).matches())
                {
                    Toast.makeText(getApplicationContext(),"Primary Phone Number is invalid!",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(str_emergencyphone.length() < 10 || !android.util.Patterns.PHONE.matcher(str_emergencyphone).matches())
                {
                    Toast.makeText(getApplicationContext(),"Emergency Phone Number is invalid!",Toast.LENGTH_LONG).show();
                    return;
                }

                try {
                    user = new User(str_email, EncryptPassword.convertPasswordMD5(str_password),str_name,str_primaryphone,str_emergencyphone);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    Log.d(null,"MD5 conversion failed");
                    return;
                }

                try {
                    registerSuccess = RegisterUser.registerUser(user);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d(null,"user registration failed");
                    return;
                }
                if(registerSuccess)
                {
                    Log.d(null,"Registration Successful");
                    Toast.makeText(getApplicationContext(), Constants.REGISTRATION_SUCCESSFUL,Toast.LENGTH_LONG).show();
                    UserEntryToDB.insertUserToDB(dbcon,user);
                    Intent intent = new Intent(Register.this, LoginActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(), Constants.EMAIL_EXISTS,Toast.LENGTH_LONG).show();

            }


        });
    }

}
