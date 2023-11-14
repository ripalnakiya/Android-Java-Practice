package com.ripalnakiya.spinnerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    ArrayList<String> arrayDocs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);

        arrayDocs.add("Adhaar Card");
        arrayDocs.add("Driving License");
        arrayDocs.add("XII Marksheet");
        arrayDocs.add("X Marksheet");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayDocs);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String str = arrayDocs.get(i);
                Toast.makeText(MainActivity.this, "Selected " + str, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
            }
        });
    }
}