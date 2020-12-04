package com.example.loginapp;

public class User {
    String userName,email,password;
    Boolean ifLoggedIn;

    public Boolean getIfLoggedIn() {
        return ifLoggedIn;
    }

    public void setIfLoggedIn(Boolean ifLoggedIn) {
        this.ifLoggedIn = ifLoggedIn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
