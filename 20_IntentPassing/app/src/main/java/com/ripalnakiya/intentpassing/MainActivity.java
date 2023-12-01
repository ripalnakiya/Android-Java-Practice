package com.ripalnakiya.intentpassing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText name;
    EditText rollno;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        rollno = findViewById(R.id.rollno);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tname = name.getText().toString();
                int trollno = Integer.parseInt(rollno.getText().toString());

                // Constructor takes two parameters i.e. (source, destination)
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                intent.putExtra("name", tname);
                intent.putExtra("rollno", trollno);

                startActivity(intent);
            }
        });
    }
}