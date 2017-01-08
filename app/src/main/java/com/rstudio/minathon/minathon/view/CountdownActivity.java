package com.rstudio.minathon.minathon.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.rstudio.minathon.minathon.R;
import com.rstudio.minathon.minathon.notification.AppService;
import com.rstudio.minathon.minathon.notification.Notifier;
import com.rstudio.minathon.minathon.view.custom.CircleTime;
import com.rstudio.minathon.minathon.view.custom.CircleTimeListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.rstudio.minathon.minathon.MainActivity.appState;

public class CountdownActivity extends AppCompatActivity {

    @BindView(R.id.imvHeader)
    ImageView imvHeader;
    @BindView(R.id.cct)
    CircleTime time;
    @BindView(R.id.my_toolbar)
    Toolbar toolbar;
    private String Groupid;
    private int timeCountdown, ringtone, timeRun;
    private Notifier mNotifier;
    private List<Integer> listRingtone = new ArrayList<>();
    public static Context appInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        appInstance = this;
        ButterKnife.bind(this);

        Glide.with(CountdownActivity.this)
                .load(R.drawable.running)
                .crossFade()
                .into(new GlideDrawableImageViewTarget(imvHeader));

        getDataInit();
        timeCountdown =15;
        time.startClock(15, new CircleTimeListener() {
            @Override
            public void onClockStart() {
                Log.d("timerun","start");
            }

            @Override
            public void onClockEnd() {
                Log.d("timerun","end");
                showActivity(OptionModeActivity.class);
                CountdownActivity.this.finish();
            }

            @Override
            public void onClockUpdate(float progress) {
                timeRun = (int)progress*timeCountdown;
                Log.d("timerun",progress+"");
            }
        });

        setupToolbar();
        turnOnNotification();
    }

    private void turnOnNotification() {
        Intent i = new Intent(this, AppService.class);
        i.putExtra("duration", (long) 60000);
        Log.d("ringtone",ringtone+"");
        if (ringtone != -1) {
            i.putExtra("tone", listRingtone.get(ringtone));
        }
        startService(i);
    }

    @Override
    protected void onPause() {
        super.onPause();
        appState = AppService.STATE_LOCKED;

        mNotifier = new Notifier(this);
        mNotifier.build("Hello", "Holle");
    }

    @Override
    protected void onPostResume() {
        Log.d("checkvaluehehe1",timeRun+"");
        super.onPostResume();
        appState = AppService.STATE_UNLOCKED;

        if (mNotifier != null)
            mNotifier.dismiss();
    }

    private void getDataInit(){
        Groupid = getIntent().getStringExtra("BikeId");
        timeCountdown = getIntent().getIntExtra("time",0);
        ringtone = getIntent().getIntExtra("ringtone",-1);

        listRingtone.add(R.raw.dien_may_xanh);
        listRingtone.add(R.raw.fart);
        listRingtone.add(R.raw.harlem_shake);
        listRingtone.add(R.raw.lac_troi);
        listRingtone.add(R.raw.ppap);
        listRingtone.add(R.raw.sieu_nhan_gao);
    }

    private void setupToolbar(){
        Groupid = Groupid==null?"":Groupid;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Group id: "+Groupid);
    }

    private void showActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }
}
