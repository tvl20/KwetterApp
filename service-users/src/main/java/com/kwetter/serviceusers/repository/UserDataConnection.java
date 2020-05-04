package com.kwetter.serviceusers.repository;

import com.kwetter.serviceusers.model.BasicUser;
import com.kwetter.serviceusers.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserDataConnection
{
	private static List<User> users = new ArrayList<User>()
	{{
		add(new User(0, "Person 1", "@Person1", "This is a very nice description of Person 1"));
		add(new User(1, "Person 2", "@Person2", "This is a very nice description of Person 2"));
		add(new User(3, "Person 3", "@Person3", "This is a very nice description of Person 3"));
	}};

	// This will later be handled with a coupling table in the database
	// <key, val[]> key = user, val[] = following userIds
	private static Map<Integer, List<Integer>> following = new HashMap<>()
	{{
		put(0, new ArrayList<>(Arrays.asList(1)));
		put(1, new ArrayList<>(Arrays.asList(0, 2)));
		put(2, new ArrayList<>(Arrays.asList(0)));
	}};

	public User getUser(int userId)
	{
		for (User user : users)
		{
			if (user.getUserID() == userId)
			{
				return user;
			}
		}

		return null;
	}

	public boolean updateUser(User updatedUser)
	{
		for (int i = 0; i < users.size(); i++)
		{
			if (users.get(i).getUserID() == updatedUser.getUserID())
			{
				users.set(i, updatedUser);
				return true;
			}
		}

		return false;
	}

	public User getUserFromMention(String mention)
	{
		for (User user : users)
		{
			if (user.getMentionHandle().equals(mention)) return user;
		}

		return null;
	}

	public BasicUser[] getFollowed(int userId)
	{
		ArrayList<BasicUser> output = new ArrayList<>();
		if (!following.containsKey(userId)) return (BasicUser[]) output.toArray();

		List<Integer> follows = following.get(userId);

		for (int i = 0; i < follows.size(); i++)
		{
			output.set(i, getUser(follows.get(i)).getBasicUser());
		}

		return (BasicUser[]) output.toArray();
	}

	// todo: find a better way of handling lookup for followers
	public BasicUser[] getFollowers(int userId)
	{
		ArrayList<BasicUser> output = new ArrayList<>();
		if (!following.containsKey(userId)) return (BasicUser[]) output.toArray();

		for (Integer user : following.keySet())
		{
			if (following.get(user).contains(userId))
			{
				output.add(getUser(user).getBasicUser());
			}
		}

		return (BasicUser[]) output.toArray();
	}

	///
	// DEBUG: TODO:REMOVE
	///
	public List<User> getAllUsers()
	{
		return Collections.unmodifiableList(users);
	}
}
