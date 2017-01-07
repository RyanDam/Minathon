package com.rstudio.minathon.minathon.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.rstudio.minathon.minathon.R;

import butterknife.BindView;

public class JoinTeamActivity extends AppCompatActivity {
    @BindView(R.id.edtTeamId)
    EditText edtTeamId;
    @BindView(R.id.edtUserName)
    EditText edtUsername;
    @BindView(R.id.btnJoinTeam)
    Button btnJoinTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_team);
    }
}
