package com.ripalnakiya.multithreading;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    TextView textView;
    private static volatile boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.startButton);
        Button stopButton = findViewById(R.id.stopButton);
        textView = findViewById(R.id.textView);

        startButton.setOnClickListener(v -> startThread());
        stopButton.setOnClickListener(v -> stopThread());
    }

    private void startThread() {
        flag = false;
        ExampleRunnable exampleRunnable = new ExampleRunnable();
        new Thread(exampleRunnable).start();
    }

    private void stopThread() {
        flag = true;
    }

    public class ExampleRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                Log.d(TAG, "startThread: " + i);
                if (flag)
                    return;
                if (i == 5) {
                    // We need to associate this handler with main thread
                    Handler handler = new Handler(Looper.getMainLooper());

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText("50%");
                        }
                    });

                    // This can work for Activity
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            textView.setText(R.string.fifty_percent);
//                        }
//                    });
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}