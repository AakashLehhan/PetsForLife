package com.aakash.petsforlife;

public class Add {
    String name, username, password;
    int pin;

    public Add(String name, String username, int pin, String password){
        this.name = name;
        this.password = password;
        this.username = username;
        this.pin = pin;
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
}
