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
        contacts.add(new Contact("Juan", "Perez", "3515555695", "Calle 10", "Masculino", "JP"));
        contacts.add(new Contact("Bruno", "Lopez", "3513589627", "Calle 11", "Masculino", "BL"));
        contacts.add(new Contact("Maria", "Martinez", "3515512986", "Calle 12", "Femenino", "MM"));
        contacts.add(new Contact("Diego", "Suarez", "3515327198", "Calle 13", "Masculino", "DS"));
        contacts.add(new Contact("Elena", "Fernandez", "3513891764", "Calle 14", "Femenino", "EF"));
        contacts.add(new Contact("Facundo", "Gonzalez", "3515988559", "Calle 15", "Masculino", "FG"));
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
