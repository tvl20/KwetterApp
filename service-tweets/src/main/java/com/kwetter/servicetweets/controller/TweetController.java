package com.kwetter.servicetweets.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwetter.servicetweets.model.*;
import com.kwetter.servicetweets.repository.TweetDao;
import com.kwetter.servicetweets.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class TweetController
{
	@Autowired
	private TweetDao tweetDao;

	@Autowired
	private UserDao userDao;

	@PostMapping("/timeline")
	@ResponseBody
	public List<Tweet> getTimelineWith(@RequestBody String wrapper)
	{
		TimelineWrappingObject nameObj = null;
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			nameObj = mapper.readValue(wrapper, TimelineWrappingObject.class);
		}
		catch (JsonProcessingException e)
		{
			e.printStackTrace();
		}

		if (nameObj != null)
		{
			return tweetDao.getTimeline(nameObj.usernames);
		}
		else return null;
	}

	@GetMapping("/posts")
	@ResponseBody
	public List<Tweet> getPostsFrom(Principal principal)
	{
		return tweetDao.getPosts(principal.getName());
	}

	@PostMapping("/posts")
	@ResponseBody
	public List<Tweet> getPostsFrom(@RequestBody String username)
	{
		return tweetDao.getPosts(username);
	}

	@PostMapping("/tweet")
	public void postTweet( Principal principal, @RequestBody String message)
	{
		Tweet tweet = new Tweet(new Date(), message, userDao.findByUsername(principal.getName()));

		System.out.println("Posting tweet: " + tweet.getPostDate().toString() + " - " + tweet.getMessage());
		tweetDao.saveAndFlush(tweet);
	}
}
