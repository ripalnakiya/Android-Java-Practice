package com.ripalnakiya.listviewapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> arrayNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        arrayNames.add("Alice");
        arrayNames.add("Bob");
        arrayNames.add("Charlie");
        arrayNames.add("David");
        arrayNames.add("Eva");
        arrayNames.add("Frank");
        arrayNames.add("Grace");
        arrayNames.add("Hannah");
        arrayNames.add("Isaac");
        arrayNames.add("Jenny");
        arrayNames.add("Kevin");
        arrayNames.add("Lily");
        arrayNames.add("Michael");
        arrayNames.add("Nora");
        arrayNames.add("Oliver");
        arrayNames.add("Pamela");
        arrayNames.add("Quinn");
        arrayNames.add("Riley");
        arrayNames.add("Samantha");
        arrayNames.add("Tyler");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayNames);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    Toast.makeText(MainActivity.this, "Click Item one", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}