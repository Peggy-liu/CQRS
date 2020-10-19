package com.cqrs.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqrs.example.coreAPI.command.AddProductCommand;
import com.cqrs.example.coreAPI.command.MarkProductAsSaleableCommand;
import com.cqrs.example.coreAPI.command.MarkProductAsUnsaleableCommand;


@Service
public class ProductCommandServiceImpl implements ProductCommandService {

	
	@Autowired
	private EventStore store;
	
	
	@Autowired
	private CommandGateway commandDispatcher;
	
	
	@Override
	public void addProductById(String id, String name) {
	
			commandDispatcher.sendAndWait(new AddProductCommand(id,name));		
	
	}

	@Override
	public void markSaleable(String id) {
		commandDispatcher.sendAndWait(new MarkProductAsSaleableCommand(id));
	}

	@Override
	public void markUnsaleable(String id) {
		commandDispatcher.sendAndWait(new MarkProductAsUnsaleableCommand(id));
		
	}

	@Override
	public List<Object> listAllEventsForOrderId(String id) {
		return store.readEvents(id).asStream().map(event -> event.getPayload()).collect(Collectors.toList());
	}

}
