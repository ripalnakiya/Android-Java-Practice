package com.ripalnakiya.autocompletetextviewapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView;

    ArrayList<String> arrayCity = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);

        arrayCity.add("Chotila");
        arrayCity.add("Jamnagar");
        arrayCity.add("Jaipur");
        arrayCity.add("Gop");
        arrayCity.add("Hirasar");
        arrayCity.add("Jodiya");
        arrayCity.add("Chobari");
        arrayCity.add("Jasdan");
        arrayCity.add("Limbdi");
        arrayCity.add("Lakhanka");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayCity);

        autoCompleteTextView.setAdapter(adapter);

        // Autocomplete will start from first character itself
        autoCompleteTextView.setThreshold(1);
    }
}