package com.query.example.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RefreshScope
public class RabbitConfiguration {

	@Value("${spring.rabbitmq.host}")
	private String hostname;

	@Value("${spring.rabbitmq.port}")
	private int port;

	@Value("${spring.rabbitmq.username}")
	private String username;

	@Value("${spring.rabbitmq.password}")
	private String password;

	@Bean
	ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(hostname, port);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		return connectionFactory;
	}

//	@Bean
//	public RabbitTemplate RabbitTemplate() {
//		RabbitTemplate template = new RabbitTemplate(connectionFactory());
//	    template.setRoutingKey("sample-queue");
//	    return template;
//	}

//	@Bean
//	public AmqpAdmin amqpAdmin() {
//		return new RabbitAdmin(connectionFactory());
//	}

//	@Bean
//	public Queue sampleQueue() {
//		return new Queue("axon-queue");
//	}


}
