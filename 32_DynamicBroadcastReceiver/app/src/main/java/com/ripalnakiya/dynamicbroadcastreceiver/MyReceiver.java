package com.ripalnakiya.dynamicbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MainActivity.this";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction() == ConnectivityManager.CONNECTIVITY_ACTION) {
            Toast.makeText(context, "Connectivity Changed", Toast.LENGTH_SHORT).show();
        }

        if (intent.getAction() == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            // returns 1 when airplane mode is ON, otherwise returns 0
            int isTurnedOn = Settings.Global.getInt(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0);

            String status = ((isTurnedOn == 1) ? "ON" : "OFF");
            Toast.makeText(context, "Airplane Mode is " + status, Toast.LENGTH_SHORT).show();
        }
    }
}