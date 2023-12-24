package com.ripalnakiya.custombroadcast;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.ripalnakiya.custombroadcast.ACTION_CUSTOM");

                String sendText;
                if (!editText.getText().toString().equals(""))
                    sendText = editText.getText().toString();
                else
                    sendText = "My Null";

                intent.putExtra("com.ripalnakiya.custombroadcast.EXTRA_VALUE", sendText);

                sendBroadcast(intent);
                Log.d("Ripal", "sent broadcast: ");
            }
        });
    }
}