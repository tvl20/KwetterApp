package com.kwetter.servicetweets.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.kwetter.servicetweets.model.BasicUser;
import com.kwetter.servicetweets.model.Tweet;
import com.kwetter.servicetweets.repository.TweetDataConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/api")
public class TweetController
{
	private static final String messagingHost = "localhost";
	private static final String nameChangeQueueName = "Name_Changes";

	@Autowired
	private TweetDataConnection tweetRepository;

	// DEBUG: TODO:REMOVE
	@GetMapping("")
	public List<BasicUser> getAllUsers()
	{
		return tweetRepository.getAllUsers();
	}

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
