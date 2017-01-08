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
import com.rstudio.minathon.minathon.firebase.FireBase;
import com.rstudio.minathon.minathon.firebase.Group;
import com.rstudio.minathon.minathon.firebase.OnListenToGroup;
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

    boolean needNoti = true;

    OnListenToGroup lsss = new OnListenToGroup() {
        @Override
        public void onGroupEnd(Group g) {
            needNoti = false;
        }

        @Override
        public void onGroupAlarm(Group g) {
            needNoti = true;
            mNotifier = new Notifier(CountdownActivity.this);
            mNotifier.build("Alarm", g.alarmName + " is using the phone!");
        }

        @Override
        public void onError(Exception e) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        appInstance = this;
        ButterKnife.bind(this);

        setupToolbar();

        Glide.with(CountdownActivity.this)
                .load(R.drawable.running)
                .crossFade()
                .into(new GlideDrawableImageViewTarget(imvHeader));

        getDataInit();

        time.startClock(timeCountdown, new CircleTimeListener() {
            @Override
            public void onClockStart() {
                needNoti = true;
                Log.d("timerun","start");
            }

            @Override
            public void onClockEnd() {
                Log.d("timerun","end");
                showActivity(OptionModeActivity.class);
                CountdownActivity.this.finish();
                needNoti = false;
            }

            @Override
            public void onClockUpdate(float progress) {
                timeRun = (int)progress*timeCountdown;
                Log.d("timerun",progress+"");
            }
        });

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
        time.pauseClock();
        if (needNoti) {
            appState = AppService.STATE_LOCKED;
            mNotifier = new Notifier(this);
            mNotifier.build("Warning", "Go back to Forbidden");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        time.resumeClock();
    }

    @Override
    protected void onPostResume() {
        Log.d("checkvaluehehe1",timeRun+"");
        super.onPostResume();
        if (needNoti) {
            appState = AppService.STATE_UNLOCKED;
            if (mNotifier != null)
                mNotifier.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
    }

    private void getDataInit(){
        Groupid = getIntent().getStringExtra("BikeId");
        FireBase.listenGroup(Groupid, lsss);
        timeCountdown = getIntent().getIntExtra("time",15);
        ringtone = getIntent().getIntExtra("ringtone",-1);
        listRingtone.add(R.raw.lac_troi);
        listRingtone.add(R.raw.ppap);
        listRingtone.add(R.raw.fart);
        listRingtone.add(R.raw.sieu_nhan_gao);
        listRingtone.add(R.raw.dien_may_xanh);
        listRingtone.add(R.raw.harlem_shake);
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
