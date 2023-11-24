package com.ripalnakiya.customdialogapp;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.custom_dialog_layout);

        // Dialog boxes are closed by default when we click on the screen outside the dialog box.
        // To cancel that functionality we use setCancelable();
        dialog.setCancelable(false);

        // We cannot directly access the button id, since button id doesn't belong to the MainActivity
        // button id belongs to the dialog box, so we can access it using the object of Dialog
        Button buttonOkay = dialog.findViewById(R.id.buttonOkay);

        buttonOkay.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, "Okay pressed", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        dialog.show();
    }
}