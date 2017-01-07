package com.rstudio.minathon.minathon.view;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;

import com.rstudio.minathon.minathon.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.rstudio.minathon.minathon.view.presenter.CreateTeamPresenter.formatTime;

public class CreateTeamActivity extends AppCompatActivity {
    @BindView(R.id.edtUserNameTeamLead)
    EditText edtUserName;
    @BindView(R.id.rabtnPeanutDrift)
    RadioButton rabtnPeanutDrift;
    @BindView(R.id.rabtnElectricBlue)
    RadioButton rabtnElectricBlue;
    @BindView(R.id.rabtnFart)
    RadioButton rabtnFart;
    @BindView(R.id.rabtnHarlemShake)
    RadioButton rabtnHarlemShake;
    @BindView(R.id.rabtnPPAP)
    RadioButton rabtnPPAP;
    @BindView(R.id.rabtnSuperSaiyan)
    RadioButton rabtnSuperSaiyan;
    @BindView(R.id.btnCreateam)
    Button btnCreateam;
    @BindView(R.id.btnSetTime)
    Button btnSetTime;
    @BindView(R.id.ckbCrazyDance)
    CheckBox ckbCrazyDance;
    @BindView(R.id.ckbDinner)
    CheckBox ckbDinner;
    @BindView(R.id.ckbMoney)
    CheckBox ckbMoney;
    @BindView(R.id.ckbNude)
    CheckBox ckbNude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);
        ButterKnife.bind(this);
        getClickButton();
    }

    private void getClickButton(){
        btnSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog mTimePicker;
                mTimePicker = onCreateDialogTimePicker();
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
    }

    private Calendar calendarResult = Calendar.getInstance();
    public Dialog onCreateDialogTimePicker() {
        final Calendar dateTime = Calendar.getInstance();
        return new TimePickerDialog(this,new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay,
                                  int minute) {
                dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                dateTime.set(Calendar.MINUTE, minute);
                btnSetTime.setText(formatTime
                        .format(dateTime.getTime()));
                calendarResult.set(Calendar.HOUR_OF_DAY, dateTime.get(Calendar.HOUR_OF_DAY));
                calendarResult.set(Calendar.MINUTE, dateTime.get(Calendar.MINUTE));

            }
        }, dateTime.get(Calendar.HOUR_OF_DAY),
                dateTime.get(Calendar.MINUTE), true);
    }
}
