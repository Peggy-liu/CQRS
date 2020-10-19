package com.query.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CqrsQueryModelApplication {

	
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(CqrsQueryModelApplication.class, args);
//		
//		ApplicationContext context =
//	            new AnnotationConfigApplicationContext(RabbitConfiguration.class);
//	        AmqpTemplate amqpTemplate = context.getBean(AmqpTemplate.class);
//	        
//	        System.out.println("Received: " + amqpTemplate.receiveAndConvert("sample-queue"));
	        
	}

}


