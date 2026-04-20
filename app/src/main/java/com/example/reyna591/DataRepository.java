package com.example.reyna591;

import java.util.ArrayList;
import java.util.List;

public class DataRepository {

    //usuario y password hardcodeado
    public static final String VALID_USERNAME = "admin";
    public static final String VALID_PASSWORD = "1234";

    private static final List<Contact> contacts = new ArrayList<>();

    //lista de contactos hardcodeada
    static {
        contacts.add(new Contact("Juan", "Perez", "3515555695", "JP"));
        contacts.add(new Contact("Bruno", "Lopez", "3513589627", "BL"));
        contacts.add(new Contact("Maria", "Martinez", "3515512986", "MM"));
        contacts.add(new Contact("Diego", "Suarez", "3515327198", "DS"));
        contacts.add(new Contact("Elena", "Fernandez", "3513891764", "EF"));
        contacts.add(new Contact("Facundo", "Gonzalez", "3515988559", "FG"));
    }

    public static boolean validateLogin(String username, String password) {
        return VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password);
    }

    public static List<Contact> getContacts() {
        return contacts;
    }

    public static void addContact(Contact contact) {
        contacts.add(contact);
    }
}
