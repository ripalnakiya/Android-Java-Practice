package com.ripalnakiya.alertdialogapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.button1) {
                // Single Button Alert Dialog Box
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.drawable.info);
                builder.setTitle("Terms & Conditions");
                builder.setMessage("Have you read all T&C ?");
                builder.setPositiveButton("I have read", (dialog, which) -> {
                    Toast.makeText(MainActivity.this, "Single Button Complete", Toast.LENGTH_SHORT).show();
                });
                builder.create().show();
            } else if (id == R.id.button2) {
                // Two Button Alert Dialog Box
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.drawable.delete);
                builder.setTitle("Delete ?");
                builder.setMessage("Are you sure you want to delete");
                builder.setPositiveButton("Yes", (dialogInterface, i) -> Toast.makeText(MainActivity.this, "Deleted!", Toast.LENGTH_SHORT).show());
                builder.setNegativeButton("No", (dialogInterface, i) -> Toast.makeText(MainActivity.this, "Deletion Canceled!", Toast.LENGTH_SHORT).show());
                builder.create().show();
            } else {
                Log.d(TAG, "No Action set for this button");
            }
        }
    };

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        // Three Button Alert Box
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setIcon(R.drawable.exit);
        builder.setTitle("Exit ?");
        builder.setMessage("Are you sure you want to Exit");
        builder.setPositiveButton("Yes", (dialogInterface, i) -> MainActivity.super.onBackPressed());
        builder.setNegativeButton("No", (dialogInterface, i) -> Toast.makeText(MainActivity.this, "Welcome Back", Toast.LENGTH_SHORT).show());
        builder.setNeutralButton("Cancel", (dialogInterface, i) -> Toast.makeText(MainActivity.this, "Exit Cancelled", Toast.LENGTH_SHORT).show());
        builder.create().show();
    }
}