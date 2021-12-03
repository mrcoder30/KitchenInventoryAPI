package com.valhallastudios.Kitcheninvmanager.controller;


import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.valhallastudios.Kitcheninvmanager.model.Product;
import com.valhallastudios.Kitcheninvmanager.repository.ProductRepository;
import com.valhallastudios.Kitcheninvmanager.service.ProductService;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class ProductController {

	// injection of productservice instance
	@Autowired
	private ProductService service;

	// list all products
	@GetMapping("/products")
	public List<Product> list() {
		return service.listAll();
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> get(@PathVariable Integer id) {
		try {
			Product product = service.get(id);
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/products")
	public ResponseEntity<Product> save(@RequestBody Product product) {
		ResponseEntity<Product> response = new ResponseEntity<Product>(this.service.save(product), 
				HttpStatus.CREATED);
		return response;
	}
  
  	@PutMapping("/products/{id}")
	public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable Integer id) {
		ResponseEntity<Product> response = new ResponseEntity<Product>(this.service.update(product, id),
				HttpStatus.ACCEPTED); //
		return response;
	}	

        @DeleteMapping("/products/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        boolean deleted = this.service.delete(id);
        if(deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@DeleteMapping("/products")
	public void deleteAll(Product product) {
		service.deleteAll(product);
	}
}

