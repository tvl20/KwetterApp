package com.kwetter.serviceauth.messaging;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration
{
	@Value("${user.new.exchange}")
	private String registerExchangeName;

	@Value("${user.remove.exchange}")
	private String removeExchangeName;

	@Value("${user.name.exchange}")
	private String nameChangeExchangeName;

	@Bean
	public FanoutExchange registerUserExchange()
	{
		return new FanoutExchange(registerExchangeName);
	}

	@Bean
	public FanoutExchange removeUserExchange()
	{
		return new FanoutExchange(removeExchangeName);
	}

	@Bean
	public FanoutExchange nameChangeExchange()
	{
		return new FanoutExchange(nameChangeExchangeName);
	}

	@Bean
	public Queue nameChangeQueue()
	{
		return new AnonymousQueue();
	}

	@Bean
	public Binding nameChangeBinding(Queue nameChangeQueue, FanoutExchange nameChangeExchange)
	{
		return BindingBuilder.bind(nameChangeQueue).to(nameChangeExchange);
	}

	@Bean
	public MessageConverter jsonMessageConverter()
	{
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory)
	{
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
}
