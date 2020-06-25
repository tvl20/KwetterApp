package com.kwetter.serviceauth.messaging;

import java.io.Serializable;

public class RabbitUser implements Serializable
{
	private String username;
	private String mentionHandle;

	public String getUsername()
	{
		return username;
	}

	public String getMentionHandle()
	{
		return mentionHandle;
	}

	public RabbitUser()
	{}

	public RabbitUser(String username)
	{
		this.username = username;
		this.mentionHandle = '@' + username;
	}
}
