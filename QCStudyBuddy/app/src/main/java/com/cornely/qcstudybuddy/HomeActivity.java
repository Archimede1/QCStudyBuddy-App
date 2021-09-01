package com.cornely.qcstudybuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cornely.qcstudybuddy.R;
import com.cornely.qcstudybuddy.appactivities.CalendarActivity;
import com.cornely.qcstudybuddy.appactivities.CourseActivity;
import com.cornely.qcstudybuddy.appactivities.DMActivity;
import com.cornely.qcstudybuddy.appactivities.FindClassActivity;
import com.cornely.qcstudybuddy.appactivities.ProfileActivity;
import com.cornely.qcstudybuddy.appactivities.SettingsActivity;
import com.cornely.qcstudybuddy.appactivities.StudyGroupActivity;
import com.cornely.qcstudybuddy.appactivities.TutorActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    public CardView profileCard, courseCard, studyGroupCard, calendarCard,
                  findClassCard, findTutorCard, messageCard, settingsCard;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileCard = (CardView) findViewById(R.id.profileCard);
        courseCard = (CardView) findViewById(R.id.courseCard);
        studyGroupCard = (CardView) findViewById(R.id.studyGroupCard);
        calendarCard = (CardView) findViewById(R.id.calendarCard);
        findClassCard = (CardView) findViewById(R.id.findClassCard);
        findTutorCard = (CardView) findViewById(R.id.findTutorCard);
        messageCard = (CardView) findViewById(R.id.messageCard);
        settingsCard = (CardView) findViewById(R.id.settingsCard);
        profileCard.setOnClickListener(this);
        courseCard.setOnClickListener(this);
        studyGroupCard.setOnClickListener(this);
        calendarCard.setOnClickListener(this);
        findClassCard.setOnClickListener(this);
        findTutorCard.setOnClickListener(this);
        messageCard.setOnClickListener(this);
        settingsCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch(v.getId()){
            case R.id.profileCard:
                i = new Intent(this, ProfileActivity.class);
                startActivity(i);
                break;
            case R.id.courseCard:
                i = new Intent(this, CourseActivity.class);
                startActivity(i);
                break;
            case R.id.studyGroupCard:
                i = new Intent(this, StudyGroupActivity.class);
                startActivity(i);
                break;
            case R.id.calendarCard:
                i = new Intent(this, CalendarActivity.class);
                startActivity(i);
                break;
            case R.id.findClassCard:
                i = new Intent(this, FindClassActivity.class);
                startActivity(i);
                break;
            case R.id.findTutorCard:
                i = new Intent(this, TutorActivity.class);
                startActivity(i);
                break;
            case R.id.messageCard:
                i = new Intent(this, DMActivity.class);
                startActivity(i);
                break;
            case R.id.settingsCard:
                i = new Intent(this, SettingsActivity.class);
                startActivity(i);
                break;
        }
    }
}