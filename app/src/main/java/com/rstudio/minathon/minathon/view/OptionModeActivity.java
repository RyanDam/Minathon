package com.rstudio.minathon.minathon.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.rstudio.minathon.minathon.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OptionModeActivity extends AppCompatActivity {
    @BindView(R.id.btnSingleMode)
    Button btnSingleMode;
    @BindView(R.id.btnTeamMode)
    Button btnTeamMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_mode);
        ButterKnife.bind(this);
        setClickButton();
    }

    private void setClickButton(){
        btnSingleMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnTeamMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showActivity(JoinTeamActivity.class);
            }
        });
    }

    private void showActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }
}
