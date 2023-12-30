package com.ripalnakiya.contactscontentprovider;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String[] permissions = {
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS
    };
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
        Button deleteButton = findViewById(R.id.deleteButton);

        addButton.setOnClickListener(this::onClick);
        deleteButton.setOnClickListener(this::onClick);

        resultView = findViewById(R.id.resultView);

        requestPermissionLauncher.launch(permissions);

        // setResult() is called after permissions are granted
    }

    private final ActivityResultLauncher<String[]> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), permissionsResult -> {
                setResult();
            });

    private void onClick(View v) {
        int id = v.getId();
        if (id == R.id.addButton) {
            String displayName = nameText.getText().toString();
            String phoneNumber = numberText.getText().toString();
            Contact contact = new Contact(displayName, phoneNumber);
            ContactHelper.addContact(getContentResolver(), contact);
        } else if (id == R.id.deleteButton) {
            String contactId = idText.getText().toString();
            ContactHelper.deleteContact(getContentResolver(), contactId);
        }
        setResult();
        resetInputFields();
    }

    void setResult() {
        List<Contact> contacts = ContactHelper.getAllContacts(getContentResolver());
        StringBuilder sb = new StringBuilder();
        for (Contact contact : contacts) {
            sb.append("ID: " + contact.getContactId() + " Name: " + contact.getDisplayName() + " Number: " + contact.getPhoneNumber() + "\n");
        }
        resultView.setText(sb);
    }

    private void resetInputFields() {
        idText.setText("");
        nameText.setText("");
        numberText.setText("");
    }
}