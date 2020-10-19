package com.cqrs.example;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cqrs.example.config.RabbitConfiguration;

@SpringBootApplication
public class CqrsCommandModelApplication {


	
	public static void main(String[] args) {
		SpringApplication.run(CqrsCommandModelApplication.class, args);
		
//		ApplicationContext context =
//	            new AnnotationConfigApplicationContext(RabbitConfiguration.class);
//	        AmqpTemplate amqpTemplate = context.getBean(AmqpTemplate.class);
//	        amqpTemplate.convertAndSend("Hello World");
//	        System.out.println("Sent: Hello World");
	}

}


