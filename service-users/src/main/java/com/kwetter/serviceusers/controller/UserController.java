package com.kwetter.serviceusers.controller;

import com.kwetter.serviceusers.model.BasicUser;
import com.kwetter.serviceusers.model.User;
import com.kwetter.serviceusers.repository.UserDataConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController
{
	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Value("${workshop.rabbitmq.exchange}")
	private String exchange;
	@Value("${workshop.rabbitmq.routingkey}")
	private String routingkey;

	@Autowired
	private UserDataConnection userDataRepository;

	// DEBUG: TODO:REMOVE
	@GetMapping("")
	public List<User> getAllUsers()
	{
		return userDataRepository.getAllUsers();
	}

	@GetMapping("/basicusermention")
	public BasicUser getBasicUserFromMention(@RequestParam String mention)
	{
		User user = userDataRepository.getUserFromMention(mention);
		return user != null ? user.getBasicUser() : null;
	}

	@GetMapping("/fulluser")
	public User getUserProfile(@RequestParam int userId)
	{
		return userDataRepository.getUser(userId);
	}

	@GetMapping("/following")
	public BasicUser[] getUserFollowing(@RequestParam int userId)
	{
		return userDataRepository.getFollowed(userId);
	}

	@PostMapping("/namechange")
	public boolean changeName(@RequestParam int userId, @RequestParam String name)
	{
		User affectedUser = userDataRepository.getUser(userId);
		affectedUser.setName(name);

		boolean success = userDataRepository.updateUser(affectedUser);

		if (!success) return false;

		System.out.println("Convert and send obj: " + affectedUser.getBasicUser().toString());
		rabbitTemplate.convertAndSend(affectedUser.getBasicUser());

		System.out.println("message sent");
		success = true;

		System.out.println("Message sent with success: " + success);
		return success;
	}
}
