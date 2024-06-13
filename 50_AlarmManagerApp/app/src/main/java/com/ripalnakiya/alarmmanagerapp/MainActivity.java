package com.ripalnakiya.alarmmanagerapp;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_1 = 1;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        Button setButton = findViewById(R.id.setButton);
        Button cancelButton = findViewById(R.id.cancelButton);

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar c = Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        c.set(Calendar.MINUTE, minute);
                        c.set(Calendar.MILLISECOND, 0);
                        setAlarm(c);
                        updateTextView(c);
                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }
        });

        requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
    }

    private void setAlarm(Calendar c) {
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE_1, intent, PendingIntent.FLAG_MUTABLE);

        AlarmManager alarmManager = getSystemService(AlarmManager.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (alarmManager.canScheduleExactAlarms()) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
            }
        }
    }

    private void updateTextView(Calendar c) {
        String AM_PM;
        if (c.get(Calendar.AM_PM) == Calendar.PM)
            AM_PM = "PM";
        else {
            AM_PM = "AM";
        }
        String text = "Alarm Set for " + c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE) + " " + AM_PM;
        textView.setText(text);
    }

    private void cancelAlarm() {
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE_1, intent, PendingIntent.FLAG_MUTABLE);
        AlarmManager alarmManager = getSystemService(AlarmManager.class);
        alarmManager.cancel(pendingIntent);
        textView.setText("Alarm Cancelled");
    }

    private final ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
        if (isGranted) {
            // Permission is granted. Continue the action or workflow in your
            // app.
        }
    });
}