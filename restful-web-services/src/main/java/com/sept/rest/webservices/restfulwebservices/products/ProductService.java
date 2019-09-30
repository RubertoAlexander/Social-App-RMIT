package com.sept.rest.webservices.restfulwebservices.products;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	@Autowired
	private ProductJpaRepository productRepository;
	
	public List<Product> findAll() {
		return this.productRepository.findAll();
	}
	
	public Product findProduct(Long id) {
		Optional<Product> optional = this.productRepository.findById(id);
		if (optional.isPresent()) {
			System.out.println(id);
			return optional.get();
		}
		return null;
	}
	
	public boolean productExist(Long id) {
		return this.productRepository.existsById(id);
	}
}
