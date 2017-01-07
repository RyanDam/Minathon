package com.rstudio.minathon.minathon.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rstudio.minathon.minathon.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JoinTeamActivity extends AppCompatActivity {
    @BindView(R.id.edtTeamId)
    EditText edtTeamId;
    @BindView(R.id.edtUserName)
    EditText edtUsername;
    @BindView(R.id.btnJoinTeam)
    Button btnJoinTeam;
    @BindView(R.id.btnCreateTeam)
    Button btnCreateTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_team);
        ButterKnife.bind(this);
        setClickButton();
    }

    private void setClickButton(){
        btnJoinTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnCreateTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showActivity(CreateTeamActivity.class);
            }
        });
    }

    private void showActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }
}
