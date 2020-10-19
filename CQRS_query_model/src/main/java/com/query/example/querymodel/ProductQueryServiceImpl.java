package com.query.example.querymodel;

import java.util.concurrent.CompletableFuture;

import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.query.example.coreapi.GetProductInfoQuery;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {

	

	@Autowired
	private QueryGateway gateway;
	

	
	@Override
	public CompletableFuture<Product> getProductById(String id) {
		return gateway.query(new GetProductInfoQuery(id), Product.class);
	}

}
