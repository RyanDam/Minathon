package com.rstudio.minathon.minathon;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rstudio.minathon.minathon.notification.AppService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static Context appInstance;
    public static int appState = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        appInstance = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, AppService.class);
        startService(i);
    }

    @Override
    protected void onPause() {
        super.onPause();
        appState = 0;
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        appState = 1;
    }
}
