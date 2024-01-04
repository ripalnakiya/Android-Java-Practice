package com.ripalnakiya.handlerthreadclass;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;

/**
 * A HandlerThread is a thread that has a built-in Looper. <br>
 * It's designed to be used for tasks that require long-running operations on a separate thread,
 * such as network operations or database transactions. <br>
 * When you create a HandlerThread, it automatically creates a Looper and starts its message loop. <br>
 * You can then create a Handler associated with this HandlerThread to post tasks to it. <br>
 */

/**
 * A Handler allows you to send and process Message and Runnable objects associated with a thread's MessageQueue. <br>
 * You create a Handler object and associate it with the main thread's Looper by default. <br>
 * This means any tasks posted to this Handler will be executed on the main thread unless specified otherwise. <br>
 */

public class ExampleHandlerThread extends HandlerThread {
    private static final String TAG = "ExampleHandlerThread";
    private Handler handler;

    public ExampleHandlerThread(String name) {
        super(name, Process.THREAD_PRIORITY_BACKGROUND);
    }

    @SuppressLint("HandlerLeak")
    @Override
    protected void onLooperPrepared() {
//        handler = new Handler();

        // Let us call MessageHandler class to handle the Message Objects.
        handler = new MessageHandler();
    }

    public Handler getHandler() {
        return handler;
    }
}
