package com.ripalnakiya.viewpagerapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerMessengerAdapter extends FragmentPagerAdapter {
    public ViewPagerMessengerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0)
            return new ChatFragment();
        else if (position == 1)
            return  new StatusFragment();
        else
            return new CallsFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0)
            return "Chat";
        else if(position == 1)
            return "Status";
        else
            return "Calls";
    }
}
