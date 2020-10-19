package com.query.example.querymodel;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqrs.example.coreAPI.event.ProductAddedEvent;
import com.cqrs.example.coreAPI.event.ProductMarkedAsSaleableEvent;
import com.cqrs.example.coreAPI.event.ProductMarkedAsUnsaleableEvent;

@Service
@ProcessingGroup("amqpEvents")
public class ProductCatalogEventHandler {

	@Autowired
	private ProductRepository repo;

	@EventHandler
	public void on(ProductAddedEvent event) {
		System.out.print("here");
		Product product = new Product(event.getProduct_id(), event.getProduct_name(), event.isSaleable());
		repo.save(product);
	}
	
	

	
	@EventHandler
	public void on(ProductMarkedAsSaleableEvent event) {
		String product_id = event.getProduct_id();
		Product found = repo.findById(product_id).orElseThrow(() -> new RuntimeException("product not found"));
		if(found.isSaleable()) {
			throw new RuntimeException("the product is already saleable");
		}
		else {
			found.setSaleable(true);
			repo.save(found);
		}
		
				
	}

	@EventHandler
	public void on(ProductMarkedAsUnsaleableEvent event) {
		String product_id = event.getProduct_id();
		Product found = repo.findById(product_id).orElseThrow(() -> new RuntimeException("product not found"));
		if(!found.isSaleable()) {
			throw new RuntimeException("the product is already unsaleable");
		}
		else {
			found.setSaleable(false);
			repo.save(found);
		}
	}

}
