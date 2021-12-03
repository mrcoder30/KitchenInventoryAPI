package com.valhallacoders.kitcheninventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.valhallacoders.kitcheninventory.model.Product;

public class ProductController {
	
	
	

	// list all products
	
	

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

}