package com.example.registration2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ViewDetailsActivity extends AppCompatActivity
{
    TextView fullDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        fullDetails = (TextView) findViewById(R.id.full_details_id);

        try {
            FileInputStream fIn = openFileInput("trial_file.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fIn);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer s = new StringBuffer();
            String lines;

            while((lines = bufferedReader.readLine())!= null)
            {
                s.append(lines + "\n");
            }
            fullDetails.setText(s.toString());
            fIn.close();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error in show details", Toast.LENGTH_LONG).show();
        }
    }
}

