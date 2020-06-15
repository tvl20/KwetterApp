package com.kwetter.serviceusers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(columnDefinition = "VARCHAR(15)")
    private String username = "";
    private String mentionHandle = "";
    private String desc = "";



    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<User> following = new HashSet<>();

    public String getMentionHandle()
    {
        return mentionHandle;
    }

    public void setMentionHandle(String mentionHandle)
    {
        this.mentionHandle = mentionHandle;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String name)
    {
        this.username = name;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public Set<User> getFollowing()
    {
        return following;
    }

    public void setFollowing(Set<User> following)
    {
        this.following = following;
    }

    public User() { }

    public User(String username, String mentionHandle)
    {
        this.username = username;
        this.mentionHandle = mentionHandle;
    }
}
