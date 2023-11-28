package com.ripalnakiya.notificationsapp;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {
    NotificationManager notificationManager;
    String CHANNEL_ID = "101";
    int id = 10;
    boolean permission = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mainButton = findViewById(R.id.mainButton);
        Button secondButton = findViewById(R.id.secondButton);

        notificationManager = getSystemService(NotificationManager.class);

        createNotificationChannel();

        mainButton.setOnClickListener(view -> {
            Notification notification = makeMainActivityNotification();
            notificationManager.notify(id++, notification);
        });

        secondButton.setOnClickListener(v -> {
            Notification notification = makeSecondActivtyNotification();
            notificationManager.notify(id++, notification);
        });

        if (!permission) {
            // Ask Permission for Sending Notifications
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
                // 'Manifest' of android package, not of application package
            }
            permission = true;
        }
    }

    private ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
        if (isGranted) {
            // Permission is granted. Continue the action or workflow in your
            // app.
        }
    });

    public void createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "NEWS", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Get latest news");

            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public Notification makeMainActivityNotification() {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.home)
                .setContentTitle("Main Activity")
                .setContentText("This notification will redirect you to Main Activity")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        return builder.build();
    }

    public Notification makeSecondActivtyNotification() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        // Without Backstack
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        // With Backstack
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addNextIntentWithParentStack(intent);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(1, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                .setSmallIcon(R.drawable.home)
                .setContentTitle("Second Activity")
                .setContentText("This notification will redirect you to Second Activity")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        return builder.build();
    }
}