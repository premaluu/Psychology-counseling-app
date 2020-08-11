package com.example.psychologycounselingapp;


public class User {

    private String user_name, user_email, user_mobile;

    public User(String username, String email, String user_mobile) {
        this.user_name = username;
        this.user_email = email;
        this.user_mobile = user_mobile;
    }



    public String getUsername() {
        return user_name;
    }

    public String getEmail() {
        return user_email;
    }

    public String getMobile() {
        return user_mobile;
    }
}