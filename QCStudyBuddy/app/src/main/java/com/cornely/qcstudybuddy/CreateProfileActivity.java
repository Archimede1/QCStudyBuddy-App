package com.cornely.qcstudybuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class CreateProfileActivity extends AppCompatActivity {

    private EditText bio, nickname, graduationDate, website, birthday, phoneNumber;
    private AutoCompleteTextView pronouns, sex;
    private Button continueBtn;

    String[] pronounList = {"he/him", "she/her", "they/them"};
    String[] listOfSexes = {"Male", "Female", "Other", "Prefer Not to Say"};

    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        bio = findViewById(R.id.bio);
        nickname = findViewById(R.id.nickname);
        graduationDate =  findViewById(R.id.graduationDate);
        website = findViewById(R.id.website);
        birthday = findViewById(R.id.birthDate);
        phoneNumber = findViewById(R.id.phoneNumber);
        pronouns = findViewById(R.id.pronouns);
        sex = findViewById(R.id.sex);
        continueBtn = findViewById(R.id.continueBtn);
        pronouns.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pronounList));
        sex.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listOfSexes));
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        graduationDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = month + "/" + year;
                        graduationDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day + "/" + month + "/" + year;
                        birthday.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }
}