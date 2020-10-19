package com.cqrs.example.coreAPI.event;

public class ProductMarkedAsUnsaleableEvent {

private String product_id;

	

	public ProductMarkedAsUnsaleableEvent(String product_id) {
	super();
	this.product_id = product_id;
}



	public String getProduct_id() {
		return product_id;
	}
}
