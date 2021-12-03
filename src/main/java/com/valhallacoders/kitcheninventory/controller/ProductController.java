package com.valhallacoders.kitcheninventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.valhallacoders.kitcheninventory.model.Product;

public class ProductController {
	
	
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable Integer id) {
		ResponseEntity<Product> response = new ResponseEntity<Product>(this.service.update(product, id),
				HttpStatus.ACCEPTED); //
		return response;
	}	


	
	
	
}