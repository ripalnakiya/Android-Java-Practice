package com.ripalnakiya.sharedpreferenceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                boolean check = sharedPreferences.getBoolean("flag", false);

                Intent iNext;
                if (check) { // If user is logged in
                    iNext = new Intent(MainActivity.this, HomeActivity.class);
                } else {    // If User is not logged in
                    iNext = new Intent(MainActivity.this, LoginActivity.class);
                }
                startActivity(iNext);
            }
        }, 2000);
    }
}