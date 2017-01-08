package com.rstudio.minathon.minathon.notification;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.rstudio.minathon.minathon.R;

/**
 * Created by LazyMonster on 07/01/2017.
 */

public class Notifier {

    private Context mContext;
    private int mId = 1000;

    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;

    public Notifier(Context c) {
        mContext = c;
        mBuilder = new NotificationCompat.Builder(mContext);
    }

    public void build(String title, String content) {
        mBuilder.setSmallIcon(R.drawable.warning)
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(false)
                .setOngoing(true);
        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(mId, mBuilder.build());
    }

    public void dismiss() {
        mNotificationManager.cancel(mId);
    }
}
