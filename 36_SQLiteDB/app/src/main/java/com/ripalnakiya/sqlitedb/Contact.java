package com.ripalnakiya.sqlitedb;

public class Contact {
    int id;
    String name;
    String number;

    public Contact() {
    }

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
