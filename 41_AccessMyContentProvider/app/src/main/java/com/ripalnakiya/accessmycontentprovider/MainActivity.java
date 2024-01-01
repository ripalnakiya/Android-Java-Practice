package com.ripalnakiya.accessmycontentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String AUTHORITY = "com.ripalnakiya.movie.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/movies");
    TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loadButton = findViewById(R.id.loadButton);
        loadButton.setOnClickListener(onClickListener);

        resultView = findViewById(R.id.resultView);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.loadButton) {
                Cursor cursor = getContentResolver().query(CONTENT_URI, null, null, null, null);
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
        }
    };
}