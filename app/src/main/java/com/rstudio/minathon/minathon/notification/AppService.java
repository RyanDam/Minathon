package com.rstudio.minathon.minathon.notification;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;

import com.rstudio.minathon.minathon.MainActivity;
import com.rstudio.minathon.minathon.R;

/**
 * Created by LazyMonster on 07/01/2017.
 */

public class AppService extends IntentService {

    public static final int STATE_LOCKED = 0;
    public static final int STATE_UNLOCKED = 1;

    private MediaPlayer mPlayer;

    public AppService() {
        super("AppService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int toneId = intent.getIntExtra("tone", R.raw.ringtone);
        mPlayer = MediaPlayer.create(MainActivity.appInstance, toneId);
        mPlayer.setLooping(true);

        // loop until time is up
        long startTime = System.currentTimeMillis();
        long duration = intent.getLongExtra("duration", 0); // time in miniseconds
        while ((System.currentTimeMillis() - startTime) < duration) {
            if (MainActivity.appState == STATE_LOCKED) {
                // app is not opening now
                // alert sound
                if (!mPlayer.isPlaying()) {
                    mPlayer.start();
                }

                // send alert to other team members

            } else {
                if (mPlayer.isPlaying()) {
                    mPlayer.pause();
                }
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }
}
