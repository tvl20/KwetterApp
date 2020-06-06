package com.kwetter.serviceauth.model;

public class AuthUser
{
	public String username;
	public String password;
	public int userid;

	public AuthUser() { }

	public AuthUser(String username, String password, int userid)
	{
		this.username = username;
		this.password = password;
		this.userid = userid;
	}
}
