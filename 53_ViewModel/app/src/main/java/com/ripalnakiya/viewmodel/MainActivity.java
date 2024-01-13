package com.ripalnakiya.viewmodel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
    MainViewModel mainViewModel;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);

        // We pass ViewModelStoreOwner to the ViewModelProvicder constructor
        // Here, we are passing the Activity as ViewModelStoreOwner
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // To load value after a configuration change, or To Load the default value when the app is started for the first time
        String text = String.valueOf(mainViewModel.counter);
        textView.setText(text);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementAndSetText();
            }
        });
    }

    private void incrementAndSetText() {
        mainViewModel.increment();
        String text = String.valueOf(mainViewModel.counter);
        textView.setText(text);
    }
}