package com.example.registration2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class LoginFormActivity extends AppCompatActivity {
    String correctPassword,correctUsername;
    String enteredUsername,enteredPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {

            /* what happens when login button pressed */
            @Override
            public void onClick(View view)
            {
                readUsernameFromFile();
                readPasswordFromFile();

                /*verifies if entered is correct or not*/
                checkLoginDetails();
            }
        });
    }
    void readUsernameFromFile()
    {
        EditText username = (EditText) findViewById(R.id.username_id);
        enteredUsername = username.getText().toString();
        try {
            FileInputStream fIn = openFileInput("username.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fIn);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer s = new StringBuffer();

            correctUsername = bufferedReader.readLine();
            fIn.close();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error in reading username password file", Toast.LENGTH_LONG).show();
        }
    }

    void readPasswordFromFile() {
        EditText password = (EditText) findViewById(R.id.password_id);
        enteredPassword = password.getText().toString();
        try {
            FileInputStream fIn = openFileInput("password.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fIn);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer s = new StringBuffer();
            correctPassword = bufferedReader.readLine();
            fIn.close();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
        }
    }
    void checkLoginDetails()
    {
        if((correctUsername.equals(enteredUsername))&&(correctPassword.equals(enteredPassword)))
        {
            Intent loginSuccessful = new Intent(LoginFormActivity.this,LoginSuccessfulActivity.class);
            startActivity(loginSuccessful);
        }
        else
        {
            if (!(correctUsername.equals(enteredUsername)))
            {
                Toast.makeText(this, "incorrect username", Toast.LENGTH_LONG).show();
            }
            if (!(correctPassword.equals(enteredPassword)))
            {
                Toast.makeText(this, "incorrect password", Toast.LENGTH_LONG).show();
            }
        }
    }

}
