package com.cqrs.example.coreAPI.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class MarkProductAsUnsaleableCommand {
	
	@TargetAggregateIdentifier
	private String product_id;

	public MarkProductAsUnsaleableCommand(String product_id) {
		super();
		this.product_id = product_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	

	
	

}
