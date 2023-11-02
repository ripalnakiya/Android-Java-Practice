package com.ripalnakiya.viewpagerapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tab = findViewById(R.id.tab);
        ViewPager viewPager = findViewById(R.id.viewPager);

        ViewPagerMessengerAdapter adapter = new ViewPagerMessengerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        tab.setupWithViewPager(viewPager);
    }
}