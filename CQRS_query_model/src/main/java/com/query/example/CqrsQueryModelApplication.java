package com.query.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
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


