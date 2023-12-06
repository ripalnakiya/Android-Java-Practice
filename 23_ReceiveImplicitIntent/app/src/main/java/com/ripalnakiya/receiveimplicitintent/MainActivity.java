package com.ripalnakiya.receiveimplicitintent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView email = findViewById(R.id.email);
        TextView subject = findViewById(R.id.subject);
        TextView body = findViewById(R.id.body);

        Intent intent = getIntent();

        String Iemail = Arrays.toString(intent.getStringArrayExtra(Intent.EXTRA_EMAIL));
        String Isubject = intent.getStringExtra(Intent.EXTRA_SUBJECT);
        String Ibody = intent.getStringExtra(Intent.EXTRA_TEXT);

        if (Iemail.equals("null"))
            Iemail = "Email";

        email.setText(Iemail);
        subject.setText(Isubject);
        body.setText(Ibody);
    }
}