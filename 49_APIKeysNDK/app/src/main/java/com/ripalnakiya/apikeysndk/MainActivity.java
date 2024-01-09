package com.ripalnakiya.apikeysndk;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("apikeysndk");
    }

    public native String getApiKey();

    public final String API_KEY = getApiKey();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView keyView = findViewById(R.id.keyView);
        keyView.setText(API_KEY);
    }
}