package com.kwetter.serviceusers.model;

public class User {
    private int userID;
    private String name;
    private String mentionHandle;
    private String desc;

    public int getUserID()
    {
        return userID;
    }

    public String getName()
    {
        return name;
    }

    public String getMentionHandle()
    {
        return mentionHandle;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public User(int userID, String name, String mentionHandle, String desc) {
        this.userID = userID;
        this.name = name;
        this.mentionHandle = mentionHandle;
        this.desc = desc;
    }

    public User() {
    }

    public BasicUser getBasicUser()
    {
        return new BasicUser(name, userID);
    }
}
