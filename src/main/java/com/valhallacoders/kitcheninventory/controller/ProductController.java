package com.valhallacoders.kitcheninventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.valhallacoders.kitcheninventory.model.Product;

public class ProductController {
	
	@PostMapping("/products")
	public ResponseEntity<Product> save(@RequestBody Product product) {
		ResponseEntity<Product> response = new ResponseEntity<Product>(this.service.save(product), 
				HttpStatus.CREATED);
		return response;
	}
	

	
	
	
	
}