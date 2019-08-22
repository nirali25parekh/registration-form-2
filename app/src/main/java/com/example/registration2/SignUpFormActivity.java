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

public class SignUpFormActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    String dateSelected="1",monthSelected="January",yearSelected="2019",sexSelected=" ",fullDetails=" ";
    String firstName=" ", lastName=" ", address=" ", email=" ", username=" ", password=" ";
    String nameFinal=" ", birthDateFinal=" ", sexSelectedFinal=" ", usernameFinal=" ";
    String addressFinal=" ", passwordFinal=" ", emailFinal=" ";
    Spinner dateSpinner,monthSpinner,yearSpinner;
    String str = "test data";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_form);

        /* set on item selected listener on date spinner */
        Spinner dateSpinner = (Spinner) findViewById(R.id.date_spinner_id);
        dateSpinner.setOnItemSelectedListener(this);

        /* set on item selected listener on month spinner */
        Spinner monthSpinner = (Spinner) findViewById(R.id.month_spinner_id);
        monthSpinner.setOnItemSelectedListener(this);

        /* set on item selected listener on year spinner */
        Spinner yearSpinner = (Spinner) findViewById(R.id.year_spinner_id);
        yearSpinner.setOnItemSelectedListener(this);

        /* set onClickListener on the save details up button */
        Button saveDetailsButton = (Button) findViewById(R.id.save_details_button_id);
        saveDetailsButton.setOnClickListener(new View.OnClickListener() {

            /* what happens when save details pressed */
            @Override
            public void onClick(View view)
            {
                /* to get all info input by the user */
                extractInfo();
                /* convert info to proper strings */
                convertInfoToString();
                /*write info to file*/
                writeAllDetailsToFile();
                writeLoginDetailsToFile();
            }
        });

        /* set onClickListener to view details */
        Button viewDetailsButton = (Button) findViewById(R.id.view_details_button_id);
        viewDetailsButton.setOnClickListener(new View.OnClickListener() {
                                                 /* what happens when view details pressed */
                                                 @Override
                                                 public void onClick(View view)
                                                 {
                                                     /* open the new page to display all */
                                                     Intent confirmationPageIntent = new Intent(SignUpFormActivity.this, ViewDetailsActivity.class);

                                                     startActivity(confirmationPageIntent);

                                                 }
                                             }
        );
    }

    /* send the whole info to file */
    void writeAllDetailsToFile()
    {

        try
        {
            String lineSeparator = System.getProperty("line.separator");
            FileOutputStream fOut = openFileOutput("trial_file.txt", Context.MODE_PRIVATE);
            fOut.write(nameFinal.getBytes());
            fOut.write(lineSeparator.getBytes());
            fOut.write(lineSeparator.getBytes());
            fOut.write(addressFinal.getBytes());
            fOut.write(lineSeparator.getBytes());
            fOut.write(lineSeparator.getBytes());
            fOut.write(emailFinal.getBytes());
            fOut.write(lineSeparator.getBytes());
            fOut.write(lineSeparator.getBytes());
            fOut.write(birthDateFinal.getBytes());
            fOut.write(lineSeparator.getBytes());
            fOut.write(lineSeparator.getBytes());
            fOut.write(sexSelectedFinal.getBytes());
            fOut.write(lineSeparator.getBytes());
            fOut.write(lineSeparator.getBytes());
            fOut.write(usernameFinal.getBytes());
            fOut.write(lineSeparator.getBytes());
            fOut.close();
            Toast.makeText(getApplicationContext(), "Details Saved Successfully", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
        }
    }

    /* send the username and password to separate files */
    void writeLoginDetailsToFile()
    {
        try {
            FileOutputStream fOutUsername = openFileOutput("username.txt", Context.MODE_PRIVATE);
            fOutUsername.write(username.getBytes());
            FileOutputStream fOutPassword = openFileOutput("password.txt", Context.MODE_PRIVATE);
            fOutPassword.write(password.getBytes());
        } catch(Exception e)
        {
            e.printStackTrace();
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
        }
    }

    /*
    starts when save details button pressed
    to get which sex in form of String
    */
    public void getSex()
    {

        RadioGroup sexRadioGroup = (RadioGroup) findViewById(R.id.sex_radio_group_id);

        //get id of selected sex
        int selectedSexId = sexRadioGroup.getCheckedRadioButtonId();

        //find the radiobutton by returned id
        RadioButton selectedSexRadioButton = (RadioButton) findViewById(selectedSexId);

        sexSelected = selectedSexRadioButton.getText().toString();

    }

    /* after save details button pressed, all the input info extracted and stored into individual strings */
    void extractInfo()
    {
        //get first name
        EditText firstNameView = (EditText) findViewById(R.id.first_name_id);
        firstName = firstNameView.getText().toString();

        //get last name
        EditText lastNameView = (EditText) findViewById(R.id.last_name_id);
        lastName = lastNameView.getText().toString();

        //get address
        EditText addressView = (EditText) findViewById(R.id.address_id);
        address = addressView.getText().toString();

        //get email id
        EditText emailView = (EditText) findViewById(R.id.email_id);
        email = emailView.getText().toString();

        //get birth date
        dateSpinner = (Spinner) findViewById(R.id.date_spinner_id);
        ArrayAdapter<CharSequence> arrayAdapterDate = ArrayAdapter.createFromResource(this,R.array.date_menu,android.R.layout.simple_spinner_dropdown_item);
        dateSpinner.setAdapter(arrayAdapterDate);
        //dateSpinner.setOnItemSelectedListener(this);

        //get birth month
        monthSpinner = (Spinner) findViewById(R.id.month_spinner_id);
        ArrayAdapter<CharSequence> arrayAdapterMonth = ArrayAdapter.createFromResource(this,R.array.month_menu,android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(arrayAdapterMonth);
        //monthSpinner.setOnItemSelectedListener(this);

        //get birth year
        yearSpinner = (Spinner) findViewById(R.id.year_spinner_id);
        ArrayAdapter<CharSequence> arrayAdapterYear = ArrayAdapter.createFromResource(this,R.array.year_menu,android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(arrayAdapterYear);
        //yearSpinner.setOnItemSelectedListener(this);

        //get sex
        getSex();

        //get username
        EditText usernameView = (EditText) findViewById(R.id.username_id);
        username = usernameView.getText().toString();

        //get password
        EditText passwordView = (EditText) findViewById(R.id.password_id);
        password = passwordView.getText().toString();

    }

    void convertInfoToString()
    {
        nameFinal = "NAME:\t" + firstName + " " + lastName;
        addressFinal = "ADDRESS:\t" + address;
        sexSelectedFinal = "SEX:\t" + sexSelected;
        birthDateFinal = "BIRTH DATE:\t" + dateSelected + " / " + monthSelected + " / " + yearSelected;
        emailFinal = "EMAIL ID:\t" + email;
        usernameFinal = "USERNAME:\t" + username;
        passwordFinal = "PASSWORD:\t" + password;

    }


    /* what values are selected by user of birth date,month and year */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l)
    {
        //whichever spinner data given is named as common
        Spinner common = (Spinner)parent;
        if(common.getId()==R.id.date_spinner_id) {
            dateSelected = common.getItemAtPosition(position).toString();
        }
        if(common.getId()==R.id.month_spinner_id) {
            monthSelected = common.getItemAtPosition(position).toString();
        }
        if(common.getId()==R.id.year_spinner_id) {
            yearSelected = common.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

