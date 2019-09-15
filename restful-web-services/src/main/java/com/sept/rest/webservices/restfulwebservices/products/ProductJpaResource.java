package com.sept.rest.webservices.restfulwebservices.products;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="*")
@RestController
public class ProductJpaResource {
	
	@Autowired
	private ProductJpaRepository productJpaRepository;

	@GetMapping("/jpa/products/all")
	public List<Product> getAllProducts(){
		return productJpaRepository.findAll();
	}

	@GetMapping("/jpa/products/{productName}")
	public List<Product> getProduct(@PathVariable String productName){
		return productJpaRepository.findByProductName(productName);
	}
	
}
