package com.example.myapplication;

import java.util.HashMap;
import java.util.Map;

public class User {
    public String Name, Surname, MiddleName;
    public Long Telephone, Password;
//    public Map<String, String> ages = new HashMap<>();
//    public var subscriptions;

    public User() {
    }

    public User(String name, String surname, String middleName, Long telephone, Long password) {

        this.Name = name;
        this.Surname = surname;
        this.MiddleName = middleName;
        this.Telephone = telephone;
        this.Password = password;
//        this.subscriptions = subscriptions;
    }
}
