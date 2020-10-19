package com.query.example.config;

import org.axonframework.config.DefaultConfigurer;
import org.axonframework.extensions.amqp.eventhandling.AMQPMessageConverter;
import org.axonframework.extensions.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.query.example.querymodel.ProductCatalogEventHandler;
import com.rabbitmq.client.Channel;

@Configuration
public class RabbitMQSpringAMQPMessageSource {

//    @Autowired
//    public RabbitMQSpringAMQPMessageSource(final AMQPMessageConverter messageConverter) {
//        super(messageConverter);
//    }
//
//    @RabbitListener(bindings = @QueueBinding(
//    		value = @Queue(value="sample-queue2"),
//    		exchange=@Exchange(value="axon-exchange") ,
//    		key="sample-queue2")
//    		)
//    @Override
//    public void onMessage(final Message message, final Channel channel) {
//        //log.debug("received message: message={}, channel={}", message, channel);
//    	System.out.println("here");
//        super.onMessage(message, channel);
//    }
	
//	@Autowired
//	public void configure(SpringAMQPMessageSource myMessageSource) {
//		 //ehConfig.registerSubscribingEventProcessor("amqpEvents", c -> myMessageSource);
//		 ProductCatalogEventHandler handler = new ProductCatalogEventHandler();
//		 SubscribingEventProcessor.builder().name("amqpEvents").messageSource(myMessageSource).
//		 eventHandlerInvoker(SimpleEventHandlerInvoker.builder().eventHandlers(new AnnotationEventHandlerAdapter(handler)).build())
//		 .build();
//		 
//	}
	
//	Configurer configurer = DefaultConfigurer.defaultConfiguration()
//            .registerEventHandler(conf -> new ProductCatalogEventHandler());

	@Bean
	public void configureEventProcessing() {
		DefaultConfigurer.defaultConfiguration().eventProcessing(c -> c.usingSubscribingEventProcessors())
		.eventProcessing(c -> c.registerEventHandler(d -> new ProductCatalogEventHandler()));
		
	}
	
	
	@Bean
	public SpringAMQPMessageSource myQueueMessageSource(AMQPMessageConverter messageConverter) {
		return new SpringAMQPMessageSource(messageConverter) {

			@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "axon-queue"), exchange = @Exchange(value = "axon-exchange"), key = "axon-key"))
			@Override
			// @RabbitListener(queues="axon-queue")
			public void onMessage(Message message, Channel channel) {
				super.onMessage(message, channel);
				System.out.print(message.getMessageProperties().getType());
			}
		};
	}

}