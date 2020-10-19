package com.query.example.querymodel;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.query.example.coreapi.GetProductInfoQuery;


@Component
public class ProductCatalogProjection {

	@Autowired
	private ProductRepository repo;
	
	
	@QueryHandler
	public Product handle(GetProductInfoQuery query) {
		return repo.findById(query.getProduct_id()).orElseThrow(() -> new RuntimeException("product not found"));
	}
	
}
