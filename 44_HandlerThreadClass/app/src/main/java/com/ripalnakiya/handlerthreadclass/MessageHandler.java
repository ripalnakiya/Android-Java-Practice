package com.ripalnakiya.handlerthreadclass;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;

public class MessageHandler extends Handler {

    private static final String TAG = "MessageHandler";
    public static final int EXAMPLE_TASK = 1;

    @Override
    public void handleMessage(@NonNull Message msg) {
        switch (msg.what) {
            case EXAMPLE_TASK:
                Log.d(TAG, "EXAMPLE_TASK -> arg1 = " + msg.arg1 + ", arg2 = " + msg.arg2);
                for (int i = 0; i < 4; i++) {
                    Log.d(TAG, "EXAMPLE_TASK " + i);
                    SystemClock.sleep(1000);
                }
                Log.d(TAG, "EXAMPLE_TASK -> Executed");
                break;
        }
    }
}
