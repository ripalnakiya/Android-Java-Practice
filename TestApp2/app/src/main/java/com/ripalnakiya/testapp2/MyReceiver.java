package com.ripalnakiya.testapp2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == "com.example.ACTION_ONE") {
            Log.d(TAG, "onReceive: ACTION_ONE");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        Log.d(TAG, "run: " + i);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
            Log.d(TAG, "onReceive: Action Completed");
        } else if (intent.getAction() == "com.example.ACTION_TWO") {
            Log.d(TAG, "onReceive: ACTION_TWO");
        } else {
            Log.d(TAG, "onReceive: No Action Set");
        }
    }
}