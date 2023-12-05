package com.ripalnakiya.sendimplicitintent;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonMail = findViewById(R.id.buttonMail);

        buttonMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);

                // This shows the apps that accept an Intent Sending Text
                intent.setType("text/plain");

                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"ripalnakiya@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Email Subject");
                intent.putExtra(Intent.EXTRA_TEXT, "Hello, This is Sample Mail");

                // Chooser shows list of all the apps that can handle text sharing
                String chooserTitle = "Share this Text with";
                Intent chooser = Intent.createChooser(intent, chooserTitle);

                try {
//                    startActivity(intent);
                    startActivity(chooser);
                } catch (ActivityNotFoundException e) {
                    Log.d("MainActivity.this", "onClick: " + e);
                }
            }
        });
    }
}