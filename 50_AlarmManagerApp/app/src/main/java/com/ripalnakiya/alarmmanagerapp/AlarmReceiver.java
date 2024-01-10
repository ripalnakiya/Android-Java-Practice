package com.ripalnakiya.alarmmanagerapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    NotificationManager notificationManager;
    String CHANNEL_ID = "101";

    @Override
    public void onReceive(Context context, Intent intent) {
        notificationManager = context.getSystemService(NotificationManager.class);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "SPORTS", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Get latest sports news");
            notificationChannel.setShowBadge(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("This is Sports NEWS")
                .setContentText("Hello World from Notification")
                .setAutoCancel(true);

        notificationManager.notify(8, builder.build());
    }
}