package com.kwetter.servicetweets.messagereceiver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwetter.servicetweets.model.BasicUser;
import com.kwetter.servicetweets.repository.TweetDataConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventReceiver
{
	@Autowired
	private TweetDataConnection tweetRepository;


	@RabbitListener(queues = "${workshop.rabbitmq.queue}")
	public void receive(String message)
	{
		System.out.println("Received event in service email:" + message);

		ObjectMapper jsonObjectMapper = new ObjectMapper();
		BasicUser basicUser = null;
		try
		{
			basicUser = jsonObjectMapper.readValue(message, BasicUser.class);
		}
		catch (JsonProcessingException e)
		{
			e.printStackTrace();
			return;
		}

		System.out.println("updating user");

		boolean success = tweetRepository.updateUser(basicUser);

		System.out.println("success: " + success);
	}
}

