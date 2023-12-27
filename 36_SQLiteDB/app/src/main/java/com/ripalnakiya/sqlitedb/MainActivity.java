package com.ripalnakiya.sqlitedb;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DBHelper dbHelper;
    EditText idText, nameText, numberText;
    TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idText = findViewById(R.id.idText);
        nameText = findViewById(R.id.nameText);
        numberText = findViewById(R.id.numberText);

        Button addButton = findViewById(R.id.addButton);
        Button updateButton = findViewById(R.id.updateButton);
        Button deleteButton = findViewById(R.id.deleteButton);

        addButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);

        resultView = findViewById(R.id.resultView);

        dbHelper = new DBHelper(this);

        if (dbHelper.fetchContacts() != null) {
            setResult();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.addButton) {
            Contact contact = new Contact(nameText.getText().toString(), numberText.getText().toString());
            dbHelper.addContact(contact);
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.updateButton) {
            Contact contact = new Contact(Integer.parseInt(idText.getText().toString()), nameText.getText().toString(), numberText.getText().toString());
            dbHelper.updateContact(contact);
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.deleteButton) {
            Contact contact = new Contact(Integer.parseInt(idText.getText().toString()), nameText.getText().toString(), numberText.getText().toString());
            dbHelper.deleteContact(contact);
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
        }
        setResult();
        resetInputFields();
    }

    void setResult() {
        ArrayList<Contact> arrContacts = dbHelper.fetchContacts();
        StringBuilder sb = new StringBuilder();
        for (Contact model : arrContacts) {
            sb.append("ID: " + model.id + "    Name: " + model.name + "    Number: " + model.number + "\n");
        }
        resultView.setText(sb);
    }

    private void resetInputFields() {
        idText.setText("");
        nameText.setText("");
        numberText.setText("");
    }
}