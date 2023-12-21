package com.ripalnakiya.myservices;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * Background Service need not to show a notification <br>
 * Service will keep running even if the {@link MainActivity} is stopped <br>
 * This Service will be stopped if the {@link MainActivity} is destroyed<br>
 */
public class MyBackgroundService extends Service {
    private static final String TAG = "MyBackgroundService";
    private static volatile boolean runThread = true;
    private Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            int counter = 0;
            while (runThread) {
                Log.d(TAG, "run: Service is Running " + ++counter);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        thread.start();
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: Service");
        // Service will be destroyed but thread will keep running, therefore using flag
        runThread = false;
    }
}