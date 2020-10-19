package com.cqrs.example.coreAPI.event;

public class ProductAddedEvent {
	
	private String product_id;
	private String product_name;
	private boolean isSaleable;
	
	public ProductAddedEvent() {
		
	}
	
	public ProductAddedEvent(String product_id, String product_name, boolean isSaleable) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.isSaleable=isSaleable;
	}

	public String getProduct_id() {
		return product_id;
	}
	
	public String getProduct_name() {
		return product_name;
	}

	public boolean isSaleable() {
		return isSaleable;
	}


	
}
