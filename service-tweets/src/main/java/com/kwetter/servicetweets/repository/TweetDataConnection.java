package com.kwetter.servicetweets.repository;

import com.kwetter.servicetweets.model.Tweet;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TweetDataConnection
{
    private static List<Tweet> tweets = new ArrayList<Tweet>(
            Arrays.asList(
                    new Tweet(new Date(), "Message 1", 0),
                    new Tweet(new Date(new Date().getTime()+1000), "Message 2", 1),
                    new Tweet(new Date(new Date().getTime()+2000), "Message 3", 0),
                    new Tweet(new Date(new Date().getTime()+3000), "Message 4", 1)
            ));

    public Tweet getLastTweetFromID(int userID)
    {
        Tweet last = null;

        for (Tweet t :
                tweets) {
            if (t.getPosterID() == userID)
            if (last == null || t.getPostDate().after(last.getPostDate()))
            { last = t;}
        }
        
        return last;
    }

    public List<Tweet> getAllTweets()
    {
        return Collections.unmodifiableList(tweets);
    }

    public List<Tweet> getAllTweetsFromID(int userID){
        List<Tweet> tweetList = new ArrayList<>();

        for (Tweet t :
                tweets) {
            if (t.getPosterID() == userID)
                tweetList.add(t);
        }

        return Collections.unmodifiableList(tweetList);
    }
}
