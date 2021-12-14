package com.aakash.petsforlife;

public class Entity {
    String name, username, password, email, animalName, animalType, animalToken, animalDesc, animalDOB;
    int pin;
    long contact;

    public Entity(String name, String username, int pin, String password){
        this.name = name;
        this.password = password;
        this.username = username;
        this.pin = pin;
    }

    public Entity(String name, String username, String email, long contact, String password){
        this.name = name;
        this.username = username;
        this.email = email;
        this.contact = contact;
        this.password = password;
    }

    public Entity(String animalName, String username, String animalType, String animalToken, String animalDesc, String animalDOB) {
        this.username = username;
        this.animalName = animalName;
        this.animalDesc = animalDesc;
        this.animalType = animalType;
        this.animalToken = animalToken;
        this.animalDOB = animalDOB;
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

    public String getAnimalName() {
        return animalName;
    }

    public String getAnimalDesc() {
        return animalDesc;
    }

    public String getAnimalDOB() {
        return animalDOB;
    }

    public String getAnimalToken() {
        return animalToken;
    }

    public String getAnimalType() {
        return animalType;
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

    public void setContact(long contact) {
        this.contact = contact;
    }

    public void setAnimalDesc(String animalDesc) {
        this.animalDesc = animalDesc;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public void setAnimalToken(String animalToken) {
        this.animalToken = animalToken;
    }

    public void setAnimalDOB(String animalDOB) {
        this.animalDOB = animalDOB;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }
}
