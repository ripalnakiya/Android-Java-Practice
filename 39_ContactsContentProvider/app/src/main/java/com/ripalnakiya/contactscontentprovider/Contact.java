package com.ripalnakiya.contactscontentprovider;

public class Contact {
    private String contactId;
    private String displayName;
    private String phoneNumber;

    public Contact(String displayName, String phoneNumbers) {
        this.displayName = displayName;
        this.phoneNumber = phoneNumbers;
    }

    public Contact(String contactId, String displayName, String phoneNumbers) {
        this.contactId = contactId;
        this.displayName = displayName;
        this.phoneNumber = phoneNumbers;
    }

    public String getContactId() {
        return contactId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
