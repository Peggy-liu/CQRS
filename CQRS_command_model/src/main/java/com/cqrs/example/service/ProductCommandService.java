package com.cqrs.example.service;

import java.util.List;

public interface ProductCommandService {

	public void addProductById(String id, String name);
	public void markSaleable(String id);
	public void markUnsaleable(String id);
	public List<Object> listAllEventsForOrderId(String id);
	
}
