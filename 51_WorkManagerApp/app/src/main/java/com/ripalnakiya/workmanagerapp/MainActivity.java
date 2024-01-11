package com.ripalnakiya.workmanagerapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Data data = new Data.Builder()
                .putInt("number", 10)
                .build();

        Constraints constraints = new Constraints.Builder()
                .setRequiresStorageNotLow(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInputData(data)
                .setConstraints(constraints)
//                .setInitialDelay(5, TimeUnit.HOURS)
                .addTag("download")
                .build();

//        WorkManager.getInstance(this).enqueue(oneTimeWorkRequest);

        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(MyWorker.class, 3, TimeUnit.DAYS)
                .setInputData(data)
                .setConstraints(constraints)
//                .setInitialDelay(5, TimeUnit.HOURS)
                .addTag("reminder")
                .build();

//        WorkManager.getInstance(this).enqueue(periodicWorkRequest);

        // Cancelling the scheduled work
//        WorkManager.getInstance(this).cancelWorkById(oneTimeWorkRequest.getId());

        // Chaining Multiple Work
        OneTimeWorkRequest oneTimeWorkRequest2 = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInputData(data)
                .setConstraints(constraints)
//                .setInitialDelay(5, TimeUnit.HOURS)
                .addTag("download")
                .build();

        WorkManager.getInstance(this).beginWith(oneTimeWorkRequest)
                .then(oneTimeWorkRequest2)
                .enqueue();
    }
}