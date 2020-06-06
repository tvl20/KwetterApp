package com.kwetter.servicetweets.controller;

import com.kwetter.servicetweets.model.BasicUser;
import com.kwetter.servicetweets.model.Tweet;
import com.kwetter.servicetweets.repository.TweetDataConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TweetController
{

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

	@PostMapping("/tweet")
	public boolean postTweet(@RequestParam int userid, @RequestBody String content)
	{
		BasicUser user = tweetRepository.getUserById(userid);
		if (user == null) return false;

		Tweet tweet = new Tweet(
				new Date(), content, user
		);

		tweetRepository.postTweet(userid, tweet);
		return true;
	}
}
