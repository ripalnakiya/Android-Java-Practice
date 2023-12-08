package com.ripalnakiya.activitylifecycle;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity.this";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d(TAG, "onCreate: B");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: B");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: B");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: B");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: B");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: B");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: B");
    }
}