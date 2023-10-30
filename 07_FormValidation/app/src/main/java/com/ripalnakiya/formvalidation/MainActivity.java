package com.ripalnakiya.formvalidation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private String gender = "";
    EditText fname;
    EditText lname;
    EditText dateText;
    RadioGroup radioGroup;
    Button submitButton;
    TextView resultView;
    private Boolean isCorrect = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fname = findViewById(R.id.fName);
        lname = findViewById(R.id.lName);
        dateText = findViewById(R.id.dateText);
        radioGroup = findViewById(R.id.radio);
        submitButton = findViewById(R.id.submitButton);
        resultView = findViewById(R.id.resultView);


        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isCorrect = performValidation();
                if(isCorrect) {
                    // Get the Value of Selected Radio Button
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(selectedId);
                    gender = radioButton.getText().toString();

                    // Show the Result
                    showResult();

                    // Clear the values
                    fname.setText("");
                    lname.setText("");
                    dateText.setText("");
                    radioButton.toggle();
                }
            }
        });
    }

    private boolean performValidation() {
        if(fname.length() == 0) {
            fname.setError("This Field is Required");
            return false;
        }
        if(lname.length() == 0) {
            lname.setError("This Field is Required");
            return false;
        }
        if(dateText.length() == 0) {
            dateText.setError("This Field is Required");
            return false;
        }
        return true;
    }

    private void openDialog() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                MainActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        dateText.setText(String.format("%d/%d/%d", datePicker.getDayOfMonth(), datePicker.getMonth(), datePicker.getYear()));
                    }
                }
                , year, month, day);

        dialog.show();
    }

    private void showResult() {
        StringBuilder sb = new StringBuilder();
        sb.append(fname.getText().toString());
        sb.append("\n");
        sb.append(lname.getText().toString());
        sb.append("\n");
        sb.append(dateText.getText().toString());
        sb.append("\n");
        sb.append(gender);
        resultView.setText(sb.toString());
    }
}