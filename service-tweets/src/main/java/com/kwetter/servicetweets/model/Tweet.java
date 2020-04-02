package com.kwetter.servicetweets.model;

import java.util.Date;

public class Tweet {
    private Date postDate;
    private String message;
    private int posterID;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public int getPosterID() {
        return posterID;
    }

    public void setPosterID(int posterID) {
        this.posterID = posterID;
    }

    public Tweet() {}

    public Tweet(Date postDate, String message, int posterID) {
        this.postDate = postDate;
        this.message = message;
        this.posterID = posterID;
    }
}
