package com.sept.rest.webservices.restfulwebservices.products;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	@Autowired
	private ProductJpaRepository productRepository;
	
	public void saveProduct(Product product) {
		productRepository.save(product);
	}
	
	public List<Product> findAll() {
		return this.productRepository.findAll();
	}
	
	public List<Product> findByProductName(String productName) {
		return productRepository.findByProductName(productName);
	}
	
	public Product findById(Long id) {
		Optional<Product> optional = this.productRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public boolean productExist(Long id) {
		return this.productRepository.existsById(id);
	}
}
