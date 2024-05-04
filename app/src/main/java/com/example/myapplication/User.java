package com.example.myapplication;

public class User {
    public String Name, surname, middleName,telephone, password;
//    public var subscriptions;

    public User() {
    }

    public User(String Name, String surname, String middleName, String telephone, String password) {

        this.Name = Name;
        this.surname = surname;
        this.middleName = middleName;
        this.telephone = telephone;
        this.password = password;
//        this.subscriptions = subscriptions;
    }
}
