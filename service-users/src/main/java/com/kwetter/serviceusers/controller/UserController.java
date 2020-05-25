package com.kwetter.serviceusers.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwetter.serviceusers.model.BasicUser;
import com.kwetter.serviceusers.model.User;
import com.kwetter.serviceusers.repository.UserDataConnection;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
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

		BasicUser updatedUser = affectedUser.getBasicUser();

		System.out.println("Creating json for: " + updatedUser.toString());
		String json;
		try
		{
			ObjectMapper objectmapper = new ObjectMapper();
			json = objectmapper.writeValueAsString(updatedUser);

			System.out.println("Constructed Json: \r\n" + json);
		}
		catch (JsonProcessingException e)
		{
			System.out.println("Error parsing JSON");
			return false;
		}

		System.out.println("Creating message");
		Message message = new Message(json.getBytes(), new MessageProperties());

		System.out.println("Sending message");
		rabbitTemplate.send(exchange, routingkey, message);

		System.out.println("message sent");
		return true;
	}
}
