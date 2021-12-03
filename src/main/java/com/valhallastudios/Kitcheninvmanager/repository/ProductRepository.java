package com.valhallastudios.Kitcheninvmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.valhallastudios.Kitcheninvmanager.model.Product;

 public interface ProductRepository extends JpaRepository<Product, Integer> {
	 
 }
