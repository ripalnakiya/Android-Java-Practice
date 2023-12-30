package com.ripalnakiya.contactscontentprovider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper {

    private static final Uri CONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    private static final String CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
    //    private static final String CONTACT_ID = ContactsContract.Contacts._ID;
    private static final String DISPLAY_NAME = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
    //    private static final String CONTACT_DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
    private static final String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

    public static List<Contact> getAllContacts(ContentResolver contentResolver) {
        List<Contact> contactList = new ArrayList<>();

        // Define the columns you want to retrieve
        String[] projection = {CONTACT_ID, DISPLAY_NAME};

        // Query the contacts content provider
        Cursor cursor = contentResolver.query(
                CONTENT_URI /*ContactsContract.Contacts.CONTENT_URI*/, projection,
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Get contact details from the cursor
                String contactId = cursor.getString(cursor.getColumnIndexOrThrow(CONTACT_ID));
                String displayName = cursor.getString(cursor.getColumnIndexOrThrow(DISPLAY_NAME));
                // Get contact phone numbers, Since there can be multiple contact numbers
                String phoneNumbers = getContactPhoneNumbers(contentResolver, contactId);

                // Create a Contact object and Add the contact to the list
                contactList.add(new Contact(contactId, displayName, phoneNumbers));
            } while (cursor.moveToNext());
        }
        return contactList;
    }

    private static String getContactPhoneNumbers(ContentResolver contentResolver, String contactId) {
        StringBuilder sb = new StringBuilder();

        // Define the columns you want to retrieve
        String[] projection = {NUMBER};

        // Query the phone numbers for the given contact ID
        Cursor cursor = contentResolver.query(
                CONTENT_URI, projection,
                CONTACT_ID + " = ?", new String[]{contactId},
                null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Get phone number from the cursor
                String phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow(NUMBER));
                sb.append(phoneNumber);
            } while (cursor.moveToNext());
        }
        return sb.toString();
    }

    /**********************************************************************************************/

    public static void addContact(ContentResolver contentResolver, Contact contact) {
        // Create a new ContentValues instance to store contact data
        ContentValues values = new ContentValues();

        // Set the account name and type to null to indicate that this contact doesn't belong to any specific account
        values.put(ContactsContract.RawContacts.ACCOUNT_NAME, (String) null);
        values.put(ContactsContract.RawContacts.ACCOUNT_TYPE, (String) null);

        // Insert the new raw contact into the Contacts Provider and get its URI
        Uri rawContactUri = contentResolver.insert(ContactsContract.RawContacts.CONTENT_URI, values);

        // Extract the raw contact ID from the URI
        long rawContactId = ContentUris.parseId(rawContactUri);

        // Clear the ContentValues for reusing it to insert name and phone number
        values.clear();

        // Set the raw contact ID for adding name
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);

        // Set the MIME type to indicate that this data entry is for a structured name
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);

        // Set the display name for the contact
        values.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, contact.getDisplayName());

        // Insert the structured name data entry into the Contacts Provider
        contentResolver.insert(ContactsContract.Data.CONTENT_URI, values);

        // Set the raw contact ID for adding phone number
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);

        // Set the MIME type to indicate that this data entry is for a phone number
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);

        // Set the phone number for the contact
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, contact.getPhoneNumber());

        // Insert the phone number data entry into the Contacts Provider
        contentResolver.insert(ContactsContract.Data.CONTENT_URI, values);
    }


    public static void deleteContact(ContentResolver contentResolver, String contactId) {
        String whereClause = ContactsContract.RawContacts._ID + " = ?";
        String[] whereArgs = {contactId};
        contentResolver.delete(ContactsContract.RawContacts.CONTENT_URI, whereClause, whereArgs);
    }
}
