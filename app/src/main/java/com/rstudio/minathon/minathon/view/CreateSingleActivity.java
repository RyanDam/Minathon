package com.rstudio.minathon.minathon.view;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.rstudio.minathon.minathon.R;
import com.rstudio.minathon.minathon.firebase.FireBase;
import com.rstudio.minathon.minathon.firebase.Group;
import com.rstudio.minathon.minathon.firebase.OnConnectGroupListener;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateSingleActivity extends AppCompatActivity {
    @BindView(R.id.edtDuration)
    EditText edtDuration;
    @BindView(R.id.radioRing)
    RadioGroup radioGroup;
    @BindView(R.id.ckbCrazyDance)
    CheckBox ckbCrazyDance;
    @BindView(R.id.ckbDinner)
    CheckBox ckbDinner;
    @BindView(R.id.ckbMoney)
    CheckBox ckbMoney;
    @BindView(R.id.ckbNude)
    CheckBox ckbNude;
    @BindView(R.id.fab_create_team)
    FloatingActionButton fabCreateTeam;

    private int mHours = 0;
    private int mMinutes = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_single);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        getClickButton();
        edtDuration.setText(mHours + ":" + mMinutes);

    }

    private void getClickButton() {
        edtDuration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog mTimePicker;
                mTimePicker = onCreateDialogTimePicker();
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        fabCreateTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Hihi", Toast.LENGTH_SHORT).show();

                createTeam();
            }
        });
    }

    public Dialog onCreateDialogTimePicker() {
        return new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mHours = hourOfDay;
                mMinutes = minute;
                edtDuration.setText(mHours + ":" + mMinutes);
            }
        }, mHours, mMinutes, true);
    }

    private void createTeam() {

        Group group = new Group();

        group.id = RandomStringUtils.randomAlphanumeric(4).toUpperCase();
        group.startTime = Calendar.getInstance().getTimeInMillis();
        group.duration = mHours * 60 + mMinutes;
        group.penaltyName = "";

        FireBase.createGroup(group, new OnConnectGroupListener() {
            @Override
            public void onSuccessful(Group g) {
                Intent i = new Intent(getApplication(), CountdownActivity.class);
                startActivity(i);
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(getApplicationContext(), "An error has occured!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
