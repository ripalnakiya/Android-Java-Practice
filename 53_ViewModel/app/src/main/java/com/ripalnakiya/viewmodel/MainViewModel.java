package com.ripalnakiya.viewmodel;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    int counter = 0;

    void increment() {
        counter++;
    }
}
