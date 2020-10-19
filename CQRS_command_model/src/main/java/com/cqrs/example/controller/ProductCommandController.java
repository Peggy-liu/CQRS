package com.cqrs.example.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cqrs.example.service.ProductCommandService;

@RestController
public class ProductCommandController {

	@Autowired
	private ProductCommandService service;

	@PostMapping("/product/{id}")
	public void addProductById(@PathVariable("id") String id, @RequestParam("name") String name,
			HttpServletResponse res) {

		service.addProductById(id, name);
		res.setStatus(HttpServletResponse.SC_CREATED);
	}

	@PutMapping("product/{id}/saleable")
	public void markSaleable(@PathVariable("id") String id, HttpServletResponse res) throws IOException {

		try {
			service.markSaleable(id);
			res.setStatus(HttpServletResponse.SC_ACCEPTED);
		} catch (IllegalStateException ex) {
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			res.getOutputStream().print("bad request");
		}
	}

	@PutMapping("product/{id}/unsaleable")
	public void markUnsaleable(@PathVariable("id") String id, HttpServletResponse res) throws IOException {

		try {
			service.markUnsaleable(id);
			res.setStatus(HttpServletResponse.SC_ACCEPTED);
		} catch (IllegalStateException ex) {
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			res.getOutputStream().print("bad request");

		}

	}
	
	@GetMapping("/product/{id}")
	public List<Object> getAllEvents(@PathVariable("id") String id){
		return service.listAllEventsForOrderId(id);
	}
}
