package com.ripalnakiya.testapp1;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {
    private static SharedPreferences sharedPreferences = null;

    public static void createInstance(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("MyOperations", MODE_PRIVATE);
        }
    }

    public static String getValue(String key) {
        return sharedPreferences.getString(key, "Not Available");
    }

    public static void storeValue(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

}
