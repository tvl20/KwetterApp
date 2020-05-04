package com.kwetter.servicetweets.model;

public class BasicUser
{
	private String username;
	private int userId;
	// todo: something with profile picture


	public String getUsername()
	{
		return username;
	}

	public int getUserId()
	{
		return userId;
	}

	public BasicUser(String username, int userId)
	{
		this.username = username;
		this.userId = userId;
	}

	public BasicUser() {}
}
