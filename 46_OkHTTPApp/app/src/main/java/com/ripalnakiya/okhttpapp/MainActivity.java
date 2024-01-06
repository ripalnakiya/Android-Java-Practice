package com.ripalnakiya.okhttpapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    OkHttpClient httpClient;
    String getURL = "https://reqres.in/api/users?page=2";
    String postURL = "https://reqres.in/api/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getButton = findViewById(R.id.getButton);
        Button postButton = findViewById(R.id.postButton);
        getButton.setOnClickListener(v -> onGetRequest());
        postButton.setOnClickListener(v -> onPostRequest());

        httpClient = new OkHttpClient();
    }

    private void onGetRequest() {
        Request request = new Request.Builder().url(getURL).build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.body() != null) {
                    Log.d(TAG, response.body().string());
                }
            }
        });
    }

    private void onPostRequest() {
        RequestBody requestBody = new FormBody.Builder()
                .add("name", "Jack")
                .add("job", "CEO")
                .build();

        Request request = new Request.Builder().url(postURL).post(requestBody).build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.body() != null) {
                    Log.d(TAG, response.body().string());
                }
            }
        });
    }
}