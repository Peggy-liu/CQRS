package com.cqrs.example.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import com.cqrs.example.coreAPI.command.AddProductCommand;
import com.cqrs.example.coreAPI.command.MarkProductAsSaleableCommand;
import com.cqrs.example.coreAPI.command.MarkProductAsUnsaleableCommand;
import com.cqrs.example.coreAPI.event.ProductAddedEvent;
import com.cqrs.example.coreAPI.event.ProductMarkedAsSaleableEvent;
import com.cqrs.example.coreAPI.event.ProductMarkedAsUnsaleableEvent;

@Aggregate
public class ProductCatalogAggregate {

	@AggregateIdentifier
	private String productid;
	private String productname;
	private boolean isSaleable = false;

	//No-arg constructor required by Axon
	public ProductCatalogAggregate() {
		
	}
	// the start of aggregate's life cycle
	@CommandHandler
	public ProductCatalogAggregate(AddProductCommand command) {
		apply(new ProductAddedEvent(command.getProduct_id(), command.getProduct_name(), false));

	}

	// handle aggregate creation event set identifier
	@EventSourcingHandler
	public void on(ProductAddedEvent event) {
		this.productid = event.getProduct_id();
		this.productname = event.getProduct_name();
	}

	@CommandHandler
	public void handle(MarkProductAsSaleableCommand command) {
		if (!this.isSaleable) {
			apply(new ProductMarkedAsSaleableEvent(command.getProduct_id()));
		} else {
			throw new IllegalStateException("the product is already marked saleable");
		}
	}

	@EventSourcingHandler
	public void on(ProductMarkedAsSaleableEvent event) {
		this.isSaleable = true;
	}

	@CommandHandler
	public void handle(MarkProductAsUnsaleableCommand command) {
		if (this.isSaleable) {
			apply(new ProductMarkedAsUnsaleableEvent(command.getProduct_id()));
		} else {
			throw new IllegalStateException("the product is already marked unsaleable");
		}
	}

	@EventSourcingHandler
	public void on(ProductMarkedAsUnsaleableEvent event) {
		this.isSaleable = false;
	}
	
	
	public String getProductid() {
		return productid;
	}
	public String getProductname() {
		return productname;
	}
	public boolean isSaleable() {
		return isSaleable;
	}


	
}
