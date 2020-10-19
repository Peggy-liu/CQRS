package com.cqrs.example.coreAPI.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class MarkProductAsSaleableCommand {
	
	@TargetAggregateIdentifier
	private String product_id;

	public MarkProductAsSaleableCommand(String product_id) {
		super();
		this.product_id = product_id;
	}

	public String getProduct_id() {
		return product_id;
	}


	
	

}
