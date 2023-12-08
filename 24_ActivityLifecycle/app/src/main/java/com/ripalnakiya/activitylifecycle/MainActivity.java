package com.ripalnakiya.activitylifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity.this";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: A");

        Button dialogButton = findViewById(R.id.dialogButton);
        Button buttonB = findViewById(R.id.buttonB);
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS);
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

    }

    private ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
        if (isGranted) {
            // Permission is granted. Continue the action or workflow in your
            // app.
        }
    });

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: A");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: A");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: A");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: A");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: A");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: A");
    }
}