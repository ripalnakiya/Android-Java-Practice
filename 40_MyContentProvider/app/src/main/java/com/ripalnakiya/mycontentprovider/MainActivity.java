package com.ripalnakiya.mycontentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText idText, nameText, rateText;
    TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idText = findViewById(R.id.idText);
        nameText = findViewById(R.id.nameText);
        rateText = findViewById(R.id.rateText);

        Button saveButton = findViewById(R.id.saveButton);
        Button deleteButton = findViewById(R.id.deleteButton);

        saveButton.setOnClickListener(onClickListener);
        deleteButton.setOnClickListener(onClickListener);

        resultView = findViewById(R.id.resultView);

        setResult();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.saveButton) {
                ContentValues values = new ContentValues();
                values.put("name", nameText.getText().toString());
                values.put("rating", rateText.getText().toString());
                Uri uri = getContentResolver().insert(MovieProvider.CONTENT_URI, values);
                Toast.makeText(MainActivity.this, uri.toString(), Toast.LENGTH_SHORT).show();
            } else if (v.getId() == R.id.deleteButton) {
                String id = idText.getText().toString();
                int row = getContentResolver().delete(MovieProvider.CONTENT_URI, "_id = ?", new String[]{id});
                Toast.makeText(MainActivity.this, "Deleted " + row, Toast.LENGTH_SHORT).show();
            }
            setResult();
            resetInputFields();
        }
    };

    private void setResult() {
        Cursor cursor = getContentResolver().query(MovieProvider.CONTENT_URI, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            StringBuilder sb = new StringBuilder();
            do {
                sb.append("ID: ");
                sb.append(cursor.getString(0));
                sb.append(" Name: ");
                sb.append(cursor.getString(1));
                sb.append(" Rating: ");
                sb.append(cursor.getString(2));
                sb.append("\n");
            } while (cursor.moveToNext());
            resultView.setText(sb.toString());
        } else {
            resultView.setText("No Records Found");
        }
    }

    private void resetInputFields() {
        idText.setText("");
        nameText.setText("");
        rateText.setText("");
    }
}