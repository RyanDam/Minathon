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

    private MediaPlayer mPlayer;

    public AppService() {
        super("AppService");
        mPlayer = MediaPlayer.create(MainActivity.appInstance, R.raw.ringtone);
        mPlayer.setLooping(true);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        while (true) {
            try {
                if (MainActivity.appState == 0) {
                    // app is not opening now
                    if (!mPlayer.isPlaying()) {
                        mPlayer.start();
                    }
                } else {
                    if (mPlayer.isPlaying()) {
                        mPlayer.pause();
                    }
                }

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }
}
