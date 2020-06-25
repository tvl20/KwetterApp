package com.kwetter.serviceusers.controller;

import com.kwetter.serviceusers.model.UserEntity;
import com.kwetter.serviceusers.model.UserStatistics;
import com.kwetter.serviceusers.repository.UserDao;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController
{
	@Autowired
	private UserDao userDao;

	@GetMapping("/details")
	@ResponseBody
	public UserEntity getUserDetails(Principal principal)
	{
		UserEntity user = userDao.findByUsername(principal.getName());
		System.out.println("RETURNING LOGGED IN USER: " + user.toString());
		return user;
	}

	@PostMapping("/details")
	@ResponseBody
	public UserEntity getUserDetails(@RequestBody String username)
	{
		UserEntity user = userDao.findByUsername(username);
		System.out.println("RETURNING USER: " + user.toString());
		return user;
	}

	@GetMapping("/stats")
	@ResponseBody
	public UserStatistics getUserStatistics(Principal principal)
	{
		UserEntity user = userDao.findByUsername(principal.getName());

		UserStatistics stats = new UserStatistics();
		stats.following = user.getFollowing().size();
		stats.followers = userDao.countUsersByFollowingContains(user);

		return stats;
	}

	@PostMapping("/stats")
	@ResponseBody
	public UserStatistics getUserStatistics(@RequestBody String username)
	{
		UserEntity user = userDao.findByUsername(username);

		UserStatistics stats = new UserStatistics();
		stats.following = user.getFollowing().size();
		stats.followers = userDao.countUsersByFollowingContains(user);

		return stats;
	}

	@GetMapping("/allusers")
	@ResponseBody
	public List<UserEntity> getAllUsers()
	{
		return userDao.findAll();
	}

	@PostMapping("/isfollowing")
	@ResponseBody
	public int isFollowingUser(Principal principal, @RequestBody String user)
	{
		UserEntity currentUser = userDao.findByUsername(principal.getName());
		UserEntity followUser = userDao.findByUsername(user);
		if (currentUser.equals(followUser)) return 2;

		for (UserEntity userEntity : currentUser.getFollowing())
		{
			if (userEntity.equals(followUser)) return 1;
		}

		return 0;
	}

	@PostMapping("/follow")
	public void followUser(Principal principal, @RequestBody String user)
	{
		System.out.println("-- HANDLING FOLLOW");
		UserEntity currentUser = userDao.findByUsername(principal.getName());
		UserEntity followUser = userDao.findByUsername(user);

		currentUser.handleFollow(followUser);

		userDao.saveAndFlush(currentUser);
	}
}
