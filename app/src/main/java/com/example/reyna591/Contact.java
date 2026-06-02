package com.example.reyna591;

public class Contact {

    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String gender;
    private String avatarText;

    //constructor para crear un contacto nuevo
    public Contact(int id, String firstName, String lastName, String phone, String address, String gender, String avatarText) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.avatarText = avatarText;
    }

    public Contact(String firstName, String lastName, String phone, String address, String gender, String avatarText) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.avatarText = avatarText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getAvatarText() {
        return avatarText;
    }

    public void setAvatarText(String avatarText) {
        this.avatarText = avatarText;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
