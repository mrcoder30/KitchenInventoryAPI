package com.valhallacoders.kitcheninventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valhallacoders.kitcheninventory.model.Product;
import com.valhallacoders.kitcheninventory.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	//injection of instance of ProductRepository
	private ProductRepository repo;
	
	//METHODS
	public List<Product> listAll() {
		return repo.findAll();
	}
	public Product save(Product product) {
		return repo.save(product);
	}
	
	public Product get(int id) {
		return repo.findById(id).get();
	}
	

	
    public boolean delete(Integer id) {
        this.repo.deleteById(id);
        //                 false      
        boolean exists = this.repo.existsById(id);
       //  true        
        return !exists;
    }
	
	public void deleteAll(Product product) {
		repo.deleteAll();
	}

	public Product update(Product product, Integer id) {
		Optional<Product> findProduct = this.repo.findById(id);
		Product productUpdate = findProduct.get();
		productUpdate.setName(product.getName());
		productUpdate.setPrice(product.getPrice());
		productUpdate.setQuantity(product.getQuantity());
		return this.repo.save(productUpdate);
	}
	
}