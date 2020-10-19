package com.query.example.querymodel;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductQueryController {

	@Autowired
	private ProductQueryService service;
	
	
	
	@GetMapping("/product/{id}")
	public Product getProductById(@PathVariable("id") String id) {
		Product product = null;
		try {
			product = service.getProductById(id).get();
		} catch (InterruptedException | ExecutionException e) {
			
			e.printStackTrace();
		}
	     return product;
	}
}
