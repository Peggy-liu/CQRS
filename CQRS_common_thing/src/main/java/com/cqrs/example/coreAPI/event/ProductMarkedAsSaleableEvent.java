package com.cqrs.example.coreAPI.event;

public class ProductMarkedAsSaleableEvent {


private String product_id;

	

	public ProductMarkedAsSaleableEvent(String product_id) {
	super();
	this.product_id = product_id;
}



	public String getProduct_id() {
		return product_id;
	}
}
