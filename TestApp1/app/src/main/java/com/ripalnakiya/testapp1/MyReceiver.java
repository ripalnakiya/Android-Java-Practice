package com.ripalnakiya.testapp1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MainActivity.this";

    @Override
    public void onReceive(Context context, Intent intent) {

        int a = intent.getIntExtra("num1", 0);
        int b = intent.getIntExtra("num2", 0);

        String operation = "";
        String result = "";

        if (intent.getAction().equals("com.example.demoapp.ADDITION")) {
            operation = "ADDITION";
            result = String.valueOf(a + b);
            Log.d(TAG, "onReceive: " + operation + " " + result);
        } else if (intent.getAction().equals("com.example.demoapp.SUBTRACTION")) {
            operation = "SUBTRACTION";
            result = String.valueOf(a - b);
            Log.d(TAG, "onReceive: " + operation + " " + result);
        } else {
            Log.d(TAG, "onReceive: No Action Added");
        }

        MySharedPreferences.storeValue("Operation", operation);
        MySharedPreferences.storeValue("Result", result);
    }
}