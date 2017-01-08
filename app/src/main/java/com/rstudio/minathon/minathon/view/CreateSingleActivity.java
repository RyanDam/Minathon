package com.rstudio.minathon.minathon.view;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.rstudio.minathon.minathon.R;
import com.rstudio.minathon.minathon.Utils;
import com.rstudio.minathon.minathon.firebase.FireBase;
import com.rstudio.minathon.minathon.firebase.Group;
import com.rstudio.minathon.minathon.firebase.OnConnectGroupListener;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateSingleActivity extends AppCompatActivity {
    @BindView(R.id.edtDuration)
    EditText edtDuration;
    @BindView(R.id.radioRing)
    RadioGroup radioGroup;
    @BindView(R.id.fab_create_team)
    FloatingActionButton fabCreateTeam;

    private int mHours = 0;
    private int mMinutes = 10;

    int checkedTone = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_single);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

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
                createTeam();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rabtnPeanutDrift:
                        checkedTone = 0;
                        break;
                    case R.id.rabtnPPAP:
                        checkedTone = 1;
                        break;
                    case R.id.rabtnFart:
                        checkedTone = 2;
                        break;
                    case R.id.rabtnSuperSaiyan:
                        checkedTone = 3;
                        break;
                    case R.id.rabtnElectricBlue:
                        checkedTone = 4;
                        break;
                    case R.id.rabtnHarlemShake:
                        checkedTone = 5;
                        break;
                }
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void createTeam() {
        final Group group = new Group();

        group.id = RandomStringUtils.randomAlphanumeric(4).toUpperCase();
        group.startTime = Calendar.getInstance().getTimeInMillis();
        group.duration = mHours * 60 * 60 + mMinutes * 60;
        group.penaltyName = "";
        group.tone = checkedTone;

        Calendar cl = Calendar.getInstance();
        group.startTime = cl.getTimeInMillis();

        Intent i = new Intent(getApplication(), CountdownActivity.class);
        i.putExtra("BikeId", group.id);
        i.putExtra("time", (int)group.duration);
        i.putExtra("ringtone", checkedTone);
        startActivity(i);

    }
}
