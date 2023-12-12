package com.ripalnakiya.dynamicfragmentapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFrag1 = findViewById(R.id.btnFrag1);
        Button btnFrag2 = findViewById(R.id.btnFrag2);
        Button btnFrag3 = findViewById(R.id.btnFrag3);

        // Load Initial Fragment
        loadFragment(new OneFragment(), true);

        btnFrag1.setOnClickListener(onClickListener);
        btnFrag2.setOnClickListener(onClickListener);
        btnFrag3.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.btnFrag1) {
                loadFragment(new OneFragment(), false);
            } else if (id == R.id.btnFrag2) {
                loadFragment(new TwoFragment(), false);
            } else if (id == R.id.btnFrag3) {
                loadFragment(new ThreeFragment(), false);
            } else {
                Log.d(TAG, "No Action set for this button");
            }
        }
    };

    public void loadFragment(Fragment fragment, boolean flag) {
        // Get Fragment Manager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Begin Fragment Transaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Perform Fragment Transaction
        if (flag) {
            fragmentTransaction.add(R.id.container, fragment);
        } else {
            fragmentTransaction.replace(R.id.container, fragment);
        }

        // Add the Fragment Transaction to the back stack
        fragmentTransaction.addToBackStack(null);

        // Commit the Fragment Transaction
        fragmentTransaction.commit();
    }
}