package com.kwetter.serviceusers.model;

public class User {
    private int userID;
    private String name;
    private String desc;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public User(int userID, String name, String desc) {
        this.userID = userID;
        this.name = name;
        this.desc = desc;
    }

    public User() {
    }
}
