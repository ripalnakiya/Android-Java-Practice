package com.ripalnakiya.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM contact")
    List<Contact> fetchContacts();
    // We use List<E> because this method returns a cursor
    // and cursor can't be directly conversed to ArrayList<E>

    @Insert
    void addContact(Contact contact);

    @Update
    void updateContact(Contact contact);

    @Delete
    void deleteContact(Contact contact);
}
// update and delete, works based on the id only