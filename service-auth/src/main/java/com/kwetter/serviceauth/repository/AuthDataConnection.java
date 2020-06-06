package com.kwetter.serviceauth.repository;

import com.kwetter.serviceauth.model.AuthUser;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthDataConnection
{
	private static List<AuthUser> users = new ArrayList<AuthUser>()
	{{
		add(new AuthUser("u1", "pass", 0));
		add(new AuthUser("u2", "pass", 1));
		add(new AuthUser("u2", "pass", 2));
	}};

	public int AuthUser(String username, String password)
	{
		for (AuthUser user : users)
		{
			if (user.username.equals(username) && user.password.equals(password)) return user.userid;
		}

		return -1;
	}
}
