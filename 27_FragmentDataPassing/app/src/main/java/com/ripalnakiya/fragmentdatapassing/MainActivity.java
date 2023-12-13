package com.ripalnakiya.fragmentdatapassing;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    FirstFragment fragment1;
    SecondFragment fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragment1 = new FirstFragment();
        fragment2 = new SecondFragment();

        Bundle args = new Bundle();
        args.putString("num", "10");
        fragment1.setArguments(args);

        fragmentTransaction.add(R.id.container1, fragment1);
        fragmentTransaction.add(R.id.container2, fragment2);
        fragmentTransaction.commit();
    }

    // This method can be directly accessed by the Fragments Hosted on this Activity using getActivity()
    public void makeToast() {
        Toast.makeText(this, "Received from Fragment 1 to Activity", Toast.LENGTH_SHORT).show();
    }

    // This global variable can be directly accessed by the Fragments Hosted on this Activity using getActivity()
    DataPassListener dataPassListener = new DataPassListener() {
        @Override
        public void onDataPass(String data) {
            fragment2.updateReceivedData(data);
        }
    };
}