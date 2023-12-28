package com.ripalnakiya.roomdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact")
public class Contact {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "number")
    String number;

    @Ignore
    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public Contact(int id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }
}
