package com.ripalnakiya.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ContactsDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CONTACTS = "contacts";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE_NO = "phone_no";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CONTACTS +
                "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NAME + " TEXT, " + KEY_PHONE_NO + " TEXT " + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(sqLiteDatabase);
    }

    public void addContact(Contact model) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, model.name);
        values.put(KEY_PHONE_NO, model.number);
        sqLiteDatabase.insert(TABLE_CONTACTS, null, values);
    }

    public ArrayList<Contact> fetchContacts() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_CONTACTS, null);
        ArrayList<Contact> arrContacts = new ArrayList<>();
        while (cursor.moveToNext()) {
            Contact model = new Contact();
            model.id = cursor.getInt(0);
            model.name = cursor.getString(1);
            model.number = cursor.getString(2);
            arrContacts.add(model);
        }
        return arrContacts;
    }

    public void updateContact(Contact model) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, model.name);
        values.put(KEY_PHONE_NO, model.number);
        sqLiteDatabase.update(TABLE_CONTACTS, values, KEY_ID + " = " + model.id, null);
    }

    public void deleteContact(Contact model) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_CONTACTS, KEY_ID + " = ?", new String[]{String.valueOf(model.id)});
    }
}
