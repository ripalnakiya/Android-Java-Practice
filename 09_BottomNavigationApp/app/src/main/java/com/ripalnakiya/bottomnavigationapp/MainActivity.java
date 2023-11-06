package com.ripalnakiya.bottomnavigationapp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavView = findViewById(R.id.bottomNavView);

        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.nav_home){
                    loadFragment(new AFragment(), false);
                } else if(id == R.id.nav_search){
                    loadFragment(new BFragment(), false);
                } else if(id == R.id.nav_post){
                    loadFragment(new CFragment(), false);
                } else if(id == R.id.nav_notifications){
                    loadFragment(new DFragment(), false);
                } else {
                    loadFragment(new EFragment(), true);
                }

                return true;
            }
        });
        bottomNavView.setSelectedItemId(R.id.nav_profile);
    }

    public void loadFragment(Fragment fragment, boolean flag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if(flag)
            ft.add(R.id.container, fragment);
        else
            ft.replace(R.id.container, fragment);

        ft.commit();
    }
}