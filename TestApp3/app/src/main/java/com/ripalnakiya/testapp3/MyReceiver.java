package com.ripalnakiya.testapp3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";
    private static int VALUE;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == "com.example.ACTION_ONE") {
            VALUE = 1;
        } else if (intent.getAction() == "com.example.ACTION_TWO") {
            VALUE = Fibonacci.findNext(5);
        } else {
            Log.d(TAG, "onReceive: No Action");
        }
        if (VALUE == 18) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("myValue", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("value", String.valueOf(VALUE)).apply();
        }
    }
}