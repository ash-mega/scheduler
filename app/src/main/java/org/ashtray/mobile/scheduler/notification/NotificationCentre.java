package org.ashtray.mobile.scheduler.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import org.ashtray.mobile.scheduler.MainActivity;
import org.ashtray.mobile.scheduler.R;
import org.ashtray.mobile.scheduler.util.Utils;

import static org.ashtray.mobile.scheduler.IConstants.CHANNEL_ID;

public class NotificationCentre {

    private Context mContext;
    private NotificationManager notificationManager;
    private Notification currentNotification;
    private RemoteViews content;

    public NotificationCentre(Context context) {
        this.mContext = context;
        notificationManager = (NotificationManager)mContext.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void showNotification(int id) {
        buildNotification();
        notificationManager.notify(id,currentNotification);
    }

    public void dismiss(int id) {
        notificationManager.cancel(id);
    }

    public void updateProgress(int progress) {
        content.setProgressBar(R.id.noti_progress,100,progress,false);
    }


    private void buildNotification() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext,CHANNEL_ID);
        content = getContentView(R.layout.notification_progress);
        Intent in = new Intent(mContext,MainActivity.class);
        PendingIntent intent = PendingIntent.getActivity(mContext,0,in,0);

        mBuilder.setSmallIcon(R.drawable.ic_launcher_foreground)
                .setOngoing(false)
                .setContentTitle("Job running")
                .setContentText("@#@")
                .setOnlyAlertOnce(true)
                .setContentIntent(intent)
                .setContent(content)
                .setWhen(System.currentTimeMillis());
        currentNotification = mBuilder.build();
    }

    private RemoteViews getContentView(int layoutId) {
        RemoteViews views = new RemoteViews(mContext.getPackageName(),layoutId);
        return views;
    }
}
