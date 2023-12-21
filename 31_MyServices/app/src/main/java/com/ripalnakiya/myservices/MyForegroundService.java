package com.ripalnakiya.myservices;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

/**
 * Foreground service must show a notification <br>
 * Service will keep running even if the {@link MainActivity} is destroyed <br>
 * This Service can be stopped from the System explicitly<br>
 * <br>
 * We has to define permission for Foreground service in Manifest file
 */
public class MyForegroundService extends Service {

    public static final String CHANNEL_ID = "11";
    MediaPlayer mp;
    Notification notification;

    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(this, R.raw.sherlock_holmes);
        mp.setLooping(true);
        createNotificationChannel();
        createNotification();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(11, notification);
        mp.start();
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNotification() {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_music)
                .setContentTitle("Music")
                .setContentText("Music is Playing")
                .setContentIntent(pendingIntent)
                .setAutoCancel(false)
                .setColorized(true)
                .setOngoing(true);

        notification = builder.build();
    }

    private void createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "Music Playing", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }

    }

    @Override
    public void onDestroy() {
        mp.stop();
        super.onDestroy();
    }
}
