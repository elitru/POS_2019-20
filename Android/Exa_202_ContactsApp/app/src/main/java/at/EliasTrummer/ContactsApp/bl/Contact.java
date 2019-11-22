package at.EliasTrummer.ContactsApp.bl;

import java.net.URL;

public class Contact {

    private final int id;
    private String firstname;
    private String lastname;
    private String language;
    private char gender;
    private String picture;
    private String phoneNumber;

    public Contact(String line) {
        String[] parts = line.split(",");

        this.id = Integer.parseInt(parts[0]);
        this.firstname = parts[2];
        this.lastname = parts[1];
        this.language = parts[3];
        this.gender = parts[4].charAt(0);
        this.picture = parts[5];
        this.phoneNumber = parts[6];
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
