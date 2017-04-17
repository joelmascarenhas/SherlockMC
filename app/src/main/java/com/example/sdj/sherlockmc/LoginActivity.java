package com.example.sdj.sherlockmc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sdj.sherlockmc.beans.User;
import com.example.sdj.sherlockmc.restlayer.AuthUserLogin;
import com.example.sdj.sherlockmc.utils.Constants;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private Button signup;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialization
        username = (EditText) findViewById(R.id.login_username);
        password = (EditText) findViewById(R.id.login_password);

        login = (Button) findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str_username = String.valueOf(username.getText());
                String str_password = String.valueOf(password.getText());
                boolean validUser = false;
                User user = new User(str_username,str_password,null,null,null);

                if (str_username.equals("") && str_password.equals("")) {
                    Toast.makeText(getApplicationContext(),"Username and Password is required!",Toast.LENGTH_LONG).show();
                }
                else if (str_password.equals("")) {
                    Toast.makeText(getApplicationContext(),"Password is required!",Toast.LENGTH_LONG).show();
                }
                else if(str_username.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Username is required!",Toast.LENGTH_LONG).show();
                }

                try {
                    validUser = AuthUserLogin.isValidUser(user);
                } catch (IOException e) {
                    e.printStackTrace();
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

        signup = (Button) findViewById(R.id.login_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, Register.class);
                startActivity(intent);
            }
        });

    }
}
