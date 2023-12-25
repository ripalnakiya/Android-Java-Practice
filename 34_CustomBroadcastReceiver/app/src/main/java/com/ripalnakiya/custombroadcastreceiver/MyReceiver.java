package com.ripalnakiya.custombroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    private TextView textView;

    public MyReceiver(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == "com.ripalnakiya.custombroadcast.ACTION_CUSTOM") {
            String receivedText = intent.getStringExtra("com.ripalnakiya.custombroadcast.EXTRA_VALUE");
            if (!receivedText.equals("null"))
                textView.setText(receivedText);
            Toast.makeText(context, "Message Changed", Toast.LENGTH_LONG).show();
        }
        Log.d("Ripal", "onReceive: Hello");
    }
}