package com.ripalnakiya.testapp2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Practice App
 */

public class MainActivity extends AppCompatActivity {
    MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myReceiver = new MyReceiver();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.ACTION_ONE");
        intentFilter.addAction("com.example.ACTION_TWO");
        registerReceiver(myReceiver, intentFilter, Context.RECEIVER_EXPORTED);

        Intent intent1 = new Intent("com.example.ACTION_ONE");
//        sendBroadcast(intent1);
        Intent intent2 = new Intent("com.example.ACTION_TWO");
//        sendBroadcast(intent2);

        AlarmManager alarmManager = getSystemService(AlarmManager.class);

        /* intent1 should be triggered after every 30 mins */
        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(MainActivity.this, 1, intent1, PendingIntent.FLAG_IMMUTABLE);
        long intervalMillis1 = 30 * 60 * 1000;   // Repeating Interval as 30 minutes
        long triggerAtMillis1 = SystemClock.elapsedRealtime() + intervalMillis1;  // Start the alarm after 30 minutes
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, triggerAtMillis1, intervalMillis1, pendingIntent1);

        /* intent2 should be triggered everyday at 9AM */
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(MainActivity.this, 2, intent2, PendingIntent.FLAG_IMMUTABLE);
        long intervalMillis2 = AlarmManager.INTERVAL_DAY;
        long triggerAtMillis2 = 9 * AlarmManager.INTERVAL_HOUR;
        if (System.currentTimeMillis() >= triggerAtMillis2)
            triggerAtMillis2 = AlarmManager.INTERVAL_DAY - System.currentTimeMillis() + triggerAtMillis2;
        else
            triggerAtMillis2 = triggerAtMillis2 - System.currentTimeMillis();
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, triggerAtMillis2, intervalMillis2, pendingIntent2);

        /* intent1 should be triggered every second */
        PendingIntent pendingIntent3 = PendingIntent.getBroadcast(MainActivity.this, 3, intent1, PendingIntent.FLAG_IMMUTABLE);
        // AlarmManager cannot work with this use case because, It requires atleast interval of 60 seconds
//        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, System.currentTimeMillis(), 1000, pendingIntent3);

        /* intent2 should be triggered every second */
        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                sendBroadcast(intent2);
//                handler.postDelayed(this, 1000);
//            }
//        }, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
}