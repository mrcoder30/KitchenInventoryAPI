package com.valhallacoders.kitcheninventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.valhallacoders.kitcheninventory.model.Product;

 public interface ProductRepository extends JpaRepository<Product, Integer> {
	 
 }
