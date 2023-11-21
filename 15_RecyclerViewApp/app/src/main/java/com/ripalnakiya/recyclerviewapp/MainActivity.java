package com.ripalnakiya.recyclerviewapp;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<ContactModel> arrayContacts = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton FAbutton = findViewById(R.id.FAbutton);

        FAbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_upadate_layout);

                EditText editName = dialog.findViewById(R.id.editName);
                EditText editNumber = dialog.findViewById(R.id.editNumber);
                Button actionButton = dialog.findViewById(R.id.actionButton);

                actionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = "", number = "";

                        if (!(editName.getText().toString().equals("")) && !(editNumber.getText().toString().equals(""))) {
                            // Make changes if details are filled properly
                            name = editName.getText().toString();
                            number = editNumber.getText().toString();

                            arrayContacts.add(new ContactModel(R.drawable.person, name, number));

                            adapter.notifyItemInserted(arrayContacts.size() - 1);
                            recyclerView.scrollToPosition(arrayContacts.size() - 1);

                            dialog.dismiss();
                        } else {
                            Toast.makeText(MainActivity.this, "Please fill all details", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ContactModel model1 = new ContactModel(R.drawable.person, "Jack", "9876543210");
        arrayContacts.add(model1);
        arrayContacts.add(new ContactModel(R.drawable.person, "John", "9876543210"));
        arrayContacts.add(new ContactModel(R.drawable.person, "James", "9876543210"));
        arrayContacts.add(new ContactModel(R.drawable.person, "Jade", "9876543210"));
        arrayContacts.add(new ContactModel(R.drawable.person, "Johnny", "9876543210"));
        arrayContacts.add(new ContactModel(R.drawable.person, "Jogger", "9876543210"));
        arrayContacts.add(new ContactModel(R.drawable.person, "Joel", "9876543210"));
        arrayContacts.add(new ContactModel(R.drawable.person, "Jasper", "9876543210"));
        arrayContacts.add(new ContactModel(R.drawable.person, "Joy", "9876543210"));
        arrayContacts.add(new ContactModel(R.drawable.person, "Julie", "9876543210"));

        adapter = new RecyclerContactAdapter(this, arrayContacts);

        recyclerView.setAdapter(adapter);
    }
}