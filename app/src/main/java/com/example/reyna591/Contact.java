package com.example.reyna591;

public class Contact {
    private final String firstName;
    private final String lastName;
    private final String phone;
    private final String avatarText;

    //constructor
    public Contact(String firstName, String lastName, String phone, String avatarText) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.avatarText = avatarText;
    }

    //getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAvatarText() {
        return avatarText;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
