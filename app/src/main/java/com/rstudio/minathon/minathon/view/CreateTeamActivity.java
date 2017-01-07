package com.rstudio.minathon.minathon.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.RadioButton;

import com.rstudio.minathon.minathon.R;

import butterknife.BindView;

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

    }

    private void getClickButton(){
        btnSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }
}
