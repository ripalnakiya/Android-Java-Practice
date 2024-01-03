package com.ripalnakiya.handlerandlooper;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class MessageHandler extends Handler {
    private static final String TAG = "MessageHandler";
    public static final int TASK_B = 2;

    @Override
    public void handleMessage(@NonNull Message msg) {
        switch (msg.what) {
            case TASK_B:
                Log.d(TAG, "Task B executed");
                break;
        }
    }
}
