package com.aakash.petsforlife;

public class Add {
    String name, username, password, email;
    int pin;
    long contact;

    public Add(String name, String username, int pin, String password){
        this.name = name;
        this.password = password;
        this.username = username;
        this.pin = pin;
    }

    public Add(String name, String username, String email, long contact, String password){
        this.name = name;
        this.username = username;
        this.email = email;
        this.contact = contact;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getPin() {
        return pin;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public long getContact() {
        return contact;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }
}
