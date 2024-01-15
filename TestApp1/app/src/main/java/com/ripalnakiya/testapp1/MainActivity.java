package com.ripalnakiya.testapp1;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Mock Interview App
 */

public class MainActivity extends AppCompatActivity {
    MyReceiver myReceiver = new MyReceiver();
    Intent intent;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MySharedPreferences.createInstance(this);

        textView = findViewById(R.id.textView);
        Button addButton = findViewById(R.id.addButton);
        Button subButton = findViewById(R.id.subButton);

        // Updating the text view with last operation
        setTextView();

        // Registering the Broadcast Receiver
        registerCustomReceiver();

        // Intent to send (With two values to operate on)
        intent = new Intent();
        intent.putExtra("num1", 10);
        intent.putExtra("num2", 5);

        addButton.setOnClickListener(onClickListener);
        subButton.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.addButton) {
                intent.setAction("com.example.demoapp.ADDITION");
            } else if (v.getId() == R.id.subButton) {
                intent.setAction("com.example.demoapp.SUBTRACTION");
            }
            sendBroadcast(intent);
        }
    };

    private void setTextView() {
        String operation = MySharedPreferences.getValue("Operation");
        String result = MySharedPreferences.getValue("Result");
        textView.setText(operation + " : " + result);
    }

    private void registerCustomReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.demoapp.ADDITION");
        filter.addAction("com.example.demoapp.SUBTRACTION");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver(myReceiver, filter, RECEIVER_EXPORTED);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregistering the Broadcast Receiver
        unregisterReceiver(myReceiver);
    }
}