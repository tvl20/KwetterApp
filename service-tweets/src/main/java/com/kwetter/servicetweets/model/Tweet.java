package com.kwetter.servicetweets.model;

import java.util.Date;

public class Tweet {
    private Date postDate;
    private String message;
    private BasicUser poster;

    public Date getPostDate()
    {
        return postDate;
    }

    public String getMessage()
    {
        return message;
    }

    public BasicUser getPoster()
    {
        return poster;
    }

    public Tweet() {}

    public Tweet(Date postDate, String message, BasicUser poster) {
        this.postDate = postDate;
        this.message = message;
        this.poster = poster;
    }
}
