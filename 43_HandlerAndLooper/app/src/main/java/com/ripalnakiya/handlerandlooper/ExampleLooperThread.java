package com.ripalnakiya.handlerandlooper;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class ExampleLooperThread extends Thread {
    private static final String TAG = "ExampleLooperThread";
    public Looper looper;
    public Handler handler;

    @Override
    public void run() {
        Looper.prepare();

        // returns looper of the current thread
        // (It is not necessary to use this looper variable, we're just using it to expose of Looper and use it outside of this class)
        looper = Looper.myLooper();

        // This handler will only handle Runnable Objects
//        handler = new Handler();

        // When handling the Message object, we use our custom Handler
        handler = new MessageHandler();
        // This handler will also handle the Runnable objects, since it contains implementation of Posting Runnables by Default

        Looper.loop();
        Log.d(TAG, "run: End of ExampleLooperThread Loop");
    }
}
