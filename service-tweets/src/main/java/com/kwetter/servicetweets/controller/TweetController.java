package com.kwetter.servicetweets.controller;

import com.kwetter.servicetweets.model.*;
import com.kwetter.servicetweets.repository.TweetDao;
import com.kwetter.servicetweets.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TweetController
{
	@Autowired
	private TweetDao tweetDao;

	@Autowired
	private UserDao userDao;

	private UserEntity ue = new UserEntity("testuser", "@testuser");

	@GetMapping("")
	private void addDebugTweet()
	{
		List<Tweet> all = tweetDao.findAll();
		if (all.size() < 1)
		{
			userDao.saveAndFlush(ue);

			Tweet t = new Tweet(new Date(), "Testing sux", ue);
			tweetDao.saveAndFlush(t);
		}
	}

	@GetMapping("/all")
	private List<Tweet> getAllTweets()
	{
		return tweetDao.findAll();
	}

	@GetMapping("/home/")
	private List<Tweet> getAllTweetsFrom()
	{
		return tweetDao.findByPoster(ue);
	}


	// DEBUG: TODO:REMOVE
//	@Autowired
//	private TweetDataConnection tweetRepository;

//	@GetMapping("")
//	public List<BasicUser> getAllUsers()
//	{
//		return tweetRepository.getAllUsers();
//	}
//
//	@GetMapping("/alltweets")
//	public List<Tweet> getAllTweets()
//	{
//		return tweetRepository.getAllTweets();
//	}
//
//	@GetMapping("/lastfromid")
//	public FrontendTweet[] getLastTweetsFrom(@RequestParam int userid)
//	{
//		return tweetRepository.getLastTweetsFromId(userid);
//	}
//
//	@GetMapping("/test")
//	public String testConnection()
//	{
//		return "Success";
//	}
//
//	@PostMapping("/tweet")
//	public boolean postTweet(@RequestParam int userid, @RequestBody String content)
//	{
//		if (tweetRepository.getBasicUserById(userid) == null) return false;
//
//		Tweet tweet = new Tweet(
//				new Date(), content, userid
//		);
//
//		tweetRepository.postTweet(userid, tweet);
//		return true;
//	}
}
