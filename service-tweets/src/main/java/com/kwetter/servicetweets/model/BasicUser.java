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

	public BasicUser() {}

	public BasicUser(String username, int userId)
	{
		this.username = username;
		this.userId = userId;
	}

	@Override
	public String toString()
	{
		return "BasicUser{" +
		       "username='" + username + '\'' +
		       ", userId=" + userId +
		       '}';
	}
}
