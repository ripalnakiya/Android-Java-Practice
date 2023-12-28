package com.ripalnakiya.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Contact.class, version = 1, exportSchema = false)
public abstract class DatabaseManager extends RoomDatabase {
    private static final String DB_NAME = "ContactsDB";
    private static DatabaseManager instance;

    public static synchronized DatabaseManager getDatabaseManager(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, DatabaseManager.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract ContactDao contactDao();
}
