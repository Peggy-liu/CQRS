package com.cqrs.example.coreAPI.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class WebCreateTodoCommand {
	
	@TargetAggregateIdentifier
	private String product_id;
	private String description;
	
	
	
	
	public WebCreateTodoCommand(String product_id, String description) {
		super();
		this.product_id = product_id;
		this.description = description;
	}




	public String getDescription() {
		return description;
	}




	public String getProduct_id() {
		return product_id;
	}
	
	

}
