package com.rstudio.minathon.minathon.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.rstudio.minathon.minathon.R;
import com.rstudio.minathon.minathon.view.custom.CircleTime;
import com.rstudio.minathon.minathon.view.custom.CircleTimeListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountdownActivity extends AppCompatActivity {

    @BindView(R.id.imvHeader)
    ImageView imvHeader;
    @BindView(R.id.cct)
    CircleTime time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        ButterKnife.bind(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Glide.with(CountdownActivity.this)
                .load(R.drawable.running)
                .crossFade()
                .into(new GlideDrawableImageViewTarget(imvHeader));


        time.startClock(65, new CircleTimeListener() {
            @Override
            public void onClockStart() {

            }

            @Override
            public void onClockEnd() {

            }

            @Override
            public void onClockUpdate(float progress) {

            }
        });
    }
}
