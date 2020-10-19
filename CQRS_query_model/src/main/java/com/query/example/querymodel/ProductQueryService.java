package com.query.example.querymodel;

import java.util.concurrent.CompletableFuture;

public interface ProductQueryService {
	
	
	public CompletableFuture<Product> getProductById(String id);
}
