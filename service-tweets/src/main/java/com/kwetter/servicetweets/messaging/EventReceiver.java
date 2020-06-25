package com.kwetter.servicetweets.messaging;

import com.kwetter.servicetweets.model.UserEntity;
import com.kwetter.servicetweets.repository.UserDao;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventReceiver
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

