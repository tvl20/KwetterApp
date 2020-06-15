package com.kwetter.servicetweets.configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageBrokerConfiguration
{

//    @Value("${workshop.rabbitmq.queue}")
//    private String queueName;
//    @Value("${workshop.rabbitmq.exchange}")
//    private String exchange;
//    @Value("${workshop.rabbitmq.routingkey}")
//    private String routingKey;
//
//    @Bean
//    public Queue queue() {
//        return new Queue(queueName);
//    }
//
//    @Bean
//    public DirectExchange exchange() {
//        return new DirectExchange(exchange);
//    }
//
//    @Bean
//    Binding binding(Queue queue, DirectExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
//    }
}
