package com.kwetter.servicetweets.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date postDate;
    private String message;

    @ManyToOne
    @JoinColumn(name = "username")
    private UserEntity username;

    public Date getPostDate()
    {
        return postDate;
    }

    public String getMessage()
    {
        return message;
    }

    public UserEntity getUsername()
    {
        return username;
    }

    public Tweet() {}

    public Tweet(Date postDate, String message, UserEntity username) {
        this.postDate = postDate;
        this.message = message;
        this.username = username;
    }
}
