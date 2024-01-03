package com.ripalnakiya.handlerandlooper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ExampleLooperThread looperThread = new ExampleLooperThread();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startButton = findViewById(R.id.startButton);
        Button taskAButton = findViewById(R.id.taskAButton);
        Button taskBButton = findViewById(R.id.taskBButton);
        Button stopButton = findViewById(R.id.stopButton);

        startButton.setOnClickListener(view -> onStartButton());
        taskAButton.setOnClickListener(view -> onTaskAButton());
        taskBButton.setOnClickListener(view -> onTaskBButton());
        stopButton.setOnClickListener(view -> onStopButton());
    }

    private void onStartButton() {
        looperThread.start();
        Toast.makeText(this, "Looper Started", Toast.LENGTH_SHORT).show();
    }

    private void onStopButton() {
        // Once, we've stopped the looper thread, It cannot start again (Just like normal thread)
        looperThread.looper.quit();

        // This can achieved alternatively (When we don't have access to Looper directly)
//        looperThread.handler.getLooper().quit();
        Toast.makeText(this, "Looper Stopped", Toast.LENGTH_SHORT).show();
    }

    private void onTaskAButton() {
//        looperThread.handler.post(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 5; i++) {
//                    Log.d(TAG, "run: " + i);
//                    SystemClock.sleep(1000);
//                }
//            }
//        });

        // Alternate method (When we don't have access to handler directly)
        Handler threadHandler = new Handler(looperThread.looper);
        // By default, Handler is initialized with the looper, In which the Handler is created
        // But if we pass a Looper to its constructor, then we can create a Handler for that particular Looper

        // new Runnable() creates a Non-static inner class, and it holds reference of MainActivity class
        // Hence it can create memory leaks
        // So, instead we can use static class that implements Runnable interface
        threadHandler.post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    Log.d(TAG, "run: " + i);
                    SystemClock.sleep(1000);
                }
                Toast.makeText(MainActivity.this, "Task A completed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onTaskBButton() {
        // Handler class can post Runnables and send Messages to the MessageQueue
        // To send a Message, we need to Implement the handling of Messages in the Handler class
        // By default, the only posting of Runnables is Implemented

        Message msg = Message.obtain();
        msg.what = 2;
        looperThread.handler.sendMessage(msg);
        Toast.makeText(this, "Task B completed", Toast.LENGTH_SHORT).show();
    }
}