package com.ripalnakiya.bmicalculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editHeight, editWeight;
        Button buttonCalculate;
        TextView textResult;

        editHeight = findViewById(R.id.editHeight);
        editWeight = findViewById(R.id.editWeight);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textResult = findViewById(R.id.textResult);

        buttonCalculate.setOnClickListener(view -> {
            double height, weight, bmi;
            height = Double.parseDouble(editHeight.getText().toString());
            weight = Double.parseDouble(editWeight.getText().toString());
            bmi = weight / (height * height);
            if(bmi > 25){
                textResult.setText("overweight");
            }
            else if(bmi < 18.5){
                textResult.setText("underweight");
            }
            else{
                textResult.setText("normal");
            }
        });
    }
}