package com.ripalnakiya.runtimepermissions;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private final String[] permissions = {
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button requestOneButton = findViewById(R.id.requestOneButton);
        Button requestMultipleButton = findViewById(R.id.requestMultipleButton);

        requestOneButton.setOnClickListener(this::onClick);
        requestMultipleButton.setOnClickListener(this::onClick);

//        // Check if permissions are granted; if not, request them
//        if (arePermissionsGranted()) {
//            // Permissions are already granted, perform your action
//        } else {
//            // Request permissions if not already granted
//            requestPermissionLauncher.launch(Arrays.toString(permissions));
//        }
    }

    private void onClick(View v) {
        int id = v.getId();
        if (id == R.id.requestOneButton) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
        } else if (id == R.id.requestMultipleButton) {
            requestPermissionsLauncher.launch(permissions);
        } else {
            Log.d(TAG, "No Action set for this button");
        }
    }

    private final ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
        if (isGranted) {
            // Permission is granted. Continue the action or workflow in your app.
        } else {
            // Explain to the user that the feature is unavailable because the feature requires a permission that the user has denied.
        }
    });

    private final ActivityResultLauncher<String[]> requestPermissionsLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), permissionsResult -> {
        // Check if all requested permissions are granted
        boolean allPermissionsGranted = true;
        for (boolean isGranted : permissionsResult.values()) {
            if (!isGranted) {
                allPermissionsGranted = false;
                break;
            }
        }

        if (allPermissionsGranted) {
            // All permissions are granted, perform your action
        } else {
            // Some or all permissions are denied, inform the user or handle accordingly
        }
    });

//    private boolean arePermissionsGranted() {
//        for (String permission : permissions) {
//            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
//                return false;
//            }
//        }
//        return true;
//    }
}