package com.kwetter.servicetweets.repository;

import com.kwetter.servicetweets.model.BasicUser;
import com.kwetter.servicetweets.model.Tweet;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TweetDataConnection
{
	private static List<Tweet> tweets = new ArrayList<Tweet>(
			Arrays.asList(
					new Tweet(new Date(), "Message 1", new BasicUser("1", 1)),
					new Tweet(new Date(new Date().getTime() + 1000), "Message 2", new BasicUser("1", 1)),
					new Tweet(new Date(new Date().getTime() + 1500), "Message 3", new BasicUser("2", 2)),
					new Tweet(new Date(new Date().getTime() + 2000), "Message 4", new BasicUser("0", 0)),
					new Tweet(new Date(new Date().getTime() + 2500), "Message 5", new BasicUser("2", 2)),
					new Tweet(new Date(new Date().getTime() + 3000), "Message 6", new BasicUser("1", 1))
			));

	private static List<BasicUser> users = new ArrayList<BasicUser>(
			Arrays.asList(
					new BasicUser("0", 0),
					new BasicUser("1", 1),
					new BasicUser("2", 2)
			));

	public Tweet getLastTweetFromID(int userID)
	{
		Tweet last = null;

		for (Tweet t : tweets)
		{
			if (t.getPoster().getUserId() == userID)
			{
				if (last == null || t.getPostDate().after(last.getPostDate()))
				{ last = t;}
			}
		}

		return last;
	}

	// DEBUG:TODO:REMOVE
	public List<Tweet> getAllTweets()
	{
		return Collections.unmodifiableList(tweets);
	}

	// DEBUG:TODO:REMOVE
	public List<BasicUser> getAllUsers()
	{
		return Collections.unmodifiableList(users);
	}

	public List<Tweet> getAllTweetsFromID(int userID)
	{
		List<Tweet> tweetList = new ArrayList<>();

		for (Tweet t : tweets)
		{
			if (t.getPoster().getUserId() == userID)
			{ tweetList.add(t); }
		}

		return Collections.unmodifiableList(tweetList);
	}

	public void updateUser(BasicUser basicUser)
	{
		for (int i = 0; i < users.size(); i++)
		{
			if (users.get(i).getUserId() == basicUser.getUserId()) users.set(i, basicUser);
		}
	}
}
