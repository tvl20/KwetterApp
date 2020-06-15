package com.kwetter.servicetweets.model;

import javax.persistence.*;

@Entity
@Table(name="users")
public class UserEntity
{
	@Id
	@Column(columnDefinition = "VARCHAR(15)")
	private String username = "";
	private String mentionHandle = "";

	public String getMentionHandle()
	{
		return mentionHandle;
	}

	public void setMentionHandle(String mentionHandle)
	{
		this.mentionHandle = mentionHandle;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public UserEntity() { }

	public UserEntity(String username, String mentionHandle)
	{
		this.username = username;
		this.mentionHandle = mentionHandle;
	}
}
