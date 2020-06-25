package com.kwetter.serviceauth.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwetter.serviceauth.model.UserEntity;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMessenger
{
	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Autowired
	private FanoutExchange registerUserExchange;

	@Autowired
	private FanoutExchange removeUserExchange;

	public void sendMessageNewUser(UserEntity newUser)
	{
		String json;
		try
		{
			ObjectMapper objectmapper = new ObjectMapper();
			json = objectmapper.writeValueAsString(new RabbitUser(newUser.getUsername()));
		}
		catch (JsonProcessingException e)
		{
			System.out.println("Error parsing JSON for new User, no message sent");
			return;
		}

		Message message = new Message(json.getBytes(), new MessageProperties());
		rabbitTemplate.send(registerUserExchange.getName(), "", message);
		System.out.println("New user created and message sent on; " + registerUserExchange.getName());
	}

	public void sendMessageRemoveUser(UserEntity oldUser)
	{
		String json;
		try
		{
			ObjectMapper objectmapper = new ObjectMapper();
			json = objectmapper.writeValueAsString(new RabbitUser(oldUser.getUsername()));
		}
		catch (JsonProcessingException e)
		{
			System.out.println("Error parsing JSON for removing User, no message sent");
			return;
		}

		Message message = new Message(json.getBytes(), new MessageProperties());
		rabbitTemplate.send(removeUserExchange.getName(), "", message);
	}
}
