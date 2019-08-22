package com.example.registration2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* set onClickListener on the sign up button */
        Button signUpButton = (Button) findViewById(R.id.sign_up_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            /* what happens when signUp pressed */
            @Override
            public void onClick(View view)
            {
                    Intent signUpIntent = new Intent(MainActivity.this,SignUpFormActivity.class);
                    startActivity(signUpIntent);

            }
        });

        /* set onClickListener to login button */
        Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
             /* what happens when login button pressed */
             @Override
             public void onClick(View view)
             {
                 /* open the new page login */
                 Intent loginIntent = new Intent(MainActivity.this, LoginFormActivity.class);

                 startActivity(loginIntent);

             }
     }
     );
    }




}

