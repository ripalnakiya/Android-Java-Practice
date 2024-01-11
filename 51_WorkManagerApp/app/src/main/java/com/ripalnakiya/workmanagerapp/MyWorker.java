package com.ripalnakiya.workmanagerapp;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {
    private static final String TAG = "MyWorker";

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    // This methods runs on background thread by default
    @NonNull
    @Override
    public Result doWork() {
        Data data = getInputData();
        int number = data.getInt("number", -1);
        Log.d(TAG, "doWork: number = " + number);

        for (int i = 0; i < number; i++) {
            Log.d(TAG, "doWork: i = " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return Result.failure();
            }
        }
        return Result.success();
    }
}
