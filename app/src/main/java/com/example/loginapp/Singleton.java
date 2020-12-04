package com.example.loginapp;

import java.util.ArrayList;

public class Singleton {
    private static Singleton singleton=null;
    User user;

    Boolean isLoggedIn;

    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    private  ArrayList<User> listOfUsers= new ArrayList<>();
    private Singleton(){

    }
    public static Singleton getInstance(){
        if(singleton==null){
            singleton= new Singleton();
        }
        return singleton;
    }
     void registerUser(User user){
        this.user=user;
        listOfUsers.add(this.user);
    }
    ArrayList<User> getUsers(){
        return this.listOfUsers;
    }
}
