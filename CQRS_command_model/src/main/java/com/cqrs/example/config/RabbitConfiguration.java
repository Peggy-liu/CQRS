package com.cqrs.example.config;

import java.util.concurrent.atomic.AtomicInteger;

import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.extensions.amqp.eventhandling.RoutingKeyResolver;
import org.axonframework.extensions.amqp.eventhandling.spring.SpringAMQPPublisher;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;



@Configuration
public class RabbitConfiguration {

	@Autowired
	private EmbeddedEventStore store;
	
	@Autowired
	private RoutingKeyResolver resolver;
	
	@Bean
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory());
		//template.setRoutingKey("sample-queue2");
		return template;
	}

	@Bean
	ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		return connectionFactory;
	}

	@Bean
	public AmqpAdmin amqpAdmin() {
		return new RabbitAdmin(connectionFactory());
	}

	@Bean
	public SpringAMQPPublisher publisher() {
		SpringAMQPPublisher publisher = new SpringAMQPPublisher(store);
		publisher.setExchangeName("axon-exchange");
		publisher.setConnectionFactory(connectionFactory());
		//publisher.setMessageConverter(converter);
		publisher.setRoutingKeyResolver(new RoutingKeyResolver() {

			@Override
			public String resolveRoutingKey(EventMessage<?> eventMessage) {
				
				return "axon-key";
			}
			
		});
		publisher.start();
		return publisher;
	}

	@Bean
	public DirectExchange exchange() {
		return new DirectExchange("axon-exchange");
	}
	
//	@Bean
//	public Queue queue() {
//		return new Queue("axon-queue");
//	}
	
//	@Bean
//	public Binding binding() {
//		return new Binding("axon-queue", DestinationType.QUEUE, "axon-exchange", "axon-key", null);
//	}
//	@Bean
//	public ScheduledProducer scheduledProducer() {
//		return new ScheduledProducer();
//	}

	@Bean
	public BeanPostProcessor postProcessor() {
		return new ScheduledAnnotationBeanPostProcessor();
	}
	
	

	static class ScheduledProducer {

		@Autowired
		private volatile RabbitTemplate rabbitTemplate;

		private final AtomicInteger counter = new AtomicInteger();

		@Scheduled(fixedRate = 3000)
		public void sendMessage() {
			rabbitTemplate.convertAndSend("Hello World " + counter.incrementAndGet());
			System.out.println("Sent: Hello World");
		}
	}
}
