package com.kwetter.servicetweets.controller;

import com.kwetter.servicetweets.model.Tweet;
import com.kwetter.servicetweets.repository.TweetDataConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TweetController {
    @Autowired
    private TweetDataConnection tweetRepository;

//    @RequestMapping(value = "/api", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/alltweets")
    public List<Tweet> getAllTweets()
    {
        return tweetRepository.getAllTweets();
    }

    @GetMapping("/allfromid")
    public List<Tweet> getAllTweetsFromID(@RequestParam int userid)
    {
        return tweetRepository.getAllTweetsFromID(userid);
    }

    @GetMapping("/lastfromid")
    public Tweet getLastTweetFrom(@RequestParam int userid)
    {
        return tweetRepository.getLastTweetFromID(userid);
    }

    @GetMapping("/test")
    public String testConnection()
    {
        return "Success";
    }
}
