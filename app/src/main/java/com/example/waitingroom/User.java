package com.example.waitingroom;

import java.util.ArrayList;

public class User {
    public String surname;
    public String name;
    public String birthday;
    public String street;
    public String postcode;
    public String city;
    public String email;
    public String password;

    public User(String surname, String name, String birthday, String street, String postcode, String city, String email, String password) {
        this.surname = surname;
        this.name = name;
        this.birthday = birthday;
        this.street = street;
        this.postcode = postcode;
        this.city = city;
        this.email = email;
        this.password = password;
    }

    public User() {
    }


    public boolean isInDatabase(ArrayList<User> allUsers){
        User user;
        for (int i = 0; i < allUsers.size(); i++){
            user = allUsers.get(i);
            if(user.email.equals(this.email)){
                return true;
            }
        }
        return false;
    }
}
