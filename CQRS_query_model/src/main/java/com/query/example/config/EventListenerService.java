package com.query.example.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EventListenerService {
	
	@RabbitListener(queues="sample-queue2")
	public void process(String data) {
		System.out.println("messgae received "+ data );
	}

}
