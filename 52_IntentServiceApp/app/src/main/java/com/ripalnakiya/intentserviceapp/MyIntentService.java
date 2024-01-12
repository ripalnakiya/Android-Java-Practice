package com.ripalnakiya.intentserviceapp;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyIntentService extends IntentService {
    private static final String TAG = "MyIntentService";
    public static final String name = "IntentWorker";

    public MyIntentService() {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int num = intent.getIntExtra("val1", 0);
        Log.d(TAG, "For intent : " + intent.getAction());
        Log.d(TAG, "Intent Value " + num);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "Task Completed");
    }
}
