package com.cqrs.example.coreAPI.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class AddProductCommand {
	
	@TargetAggregateIdentifier
	private String product_id;
	private String product_name;
	
	
	
	public AddProductCommand(String product_id, String product_name) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
	}



	public String getProduct_id() {
		return product_id;
	}



	public String getProduct_name() {
		return product_name;
	}


	public AddProductCommand() {
		
	}

	
	
	
}
