package com.rstudio.minathon.minathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.rstudio.minathon.minathon.view.custom.CircleTime;
import com.rstudio.minathon.minathon.view.custom.CircleTimeListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CircleTime cl = ((CircleTime) findViewById(R.id.time));

        Button bt = ((Button) findViewById(R.id.start));

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cl.startClock(65, new CircleTimeListener() {
                    @Override
                    public void onClockStart() {
                        Log.d(TAG, "onClockStart: ");
                    }

                    @Override
                    public void onClockEnd() {
                        Log.d(TAG, "onClockEnd: ");
                    }

                    @Override
                    public void onClockUpdate(float progress) {
                        Log.d(TAG, "onClockUpdate: ");
                    }
                });
            }
        });

    }
}
