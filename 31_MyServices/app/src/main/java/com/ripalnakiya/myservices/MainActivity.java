package com.ripalnakiya.myservices;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public boolean isPermitted = false;
    Intent foregroundIntent;
    Intent backgroundIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");

        Button startForeground = findViewById(R.id.startForeground);
        Button stopForeground = findViewById(R.id.stopForeground);
        Button startBackground = findViewById(R.id.startBackground);
        Button stopBackground = findViewById(R.id.stopBackground);

        startForeground.setOnClickListener(onClickListener);
        stopForeground.setOnClickListener(onClickListener);
        startBackground.setOnClickListener(onClickListener);
        stopBackground.setOnClickListener(onClickListener);

        if (!isPermitted) {
            requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS);
        }

        foregroundIntent = new Intent(MainActivity.this, MyForegroundService.class);
        backgroundIntent = new Intent(MainActivity.this, MyBackgroundService.class);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.startForeground) {
                startForegroundService(foregroundIntent);
            } else if (v.getId() == R.id.stopForeground) {
                stopService(foregroundIntent);
            } else if (v.getId() == R.id.startBackground) {
                startService(backgroundIntent);
            } else if (v.getId() == R.id.stopBackground) {
                stopService(backgroundIntent);
            } else {
                Log.d(TAG, "onClick: No Action set for this ClickListener");
            }
        }
    };

    private ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
        if (isGranted) {
            // Permission is granted. Continue the action or workflow in your app.
            isPermitted = true;
        }
    });

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}