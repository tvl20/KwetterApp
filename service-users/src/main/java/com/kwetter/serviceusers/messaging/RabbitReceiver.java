package com.kwetter.serviceusers.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwetter.serviceusers.model.UserEntity;
import com.kwetter.serviceusers.repository.UserDao;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitReceiver
{
	@Autowired
	private UserDao userDao;

	@RabbitListener(queues = "#{newUserQueue.name}")
	private void registerNewUser(UserEntity registeredUser)
	{
		userDao.saveAndFlush(registeredUser);
		System.out.println("NEW USER ADDED: " + registeredUser.getUsername() + ", " +registeredUser.getMentionHandle());
		System.out.println(registeredUser);
	}
}
