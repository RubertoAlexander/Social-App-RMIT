package com.sept.rest.webservices.restfulwebservices.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class ProductJpaResource {
	
	@Autowired
	private ProductHardcodedService productService;
	
	@Autowired
	private ProductJpaRepository productJpaRepository;

	@GetMapping("/jpa/products/all")
	public List<Product> getAllProducts(){
		return productJpaRepository.findAll();
		//return productService.findAll();
	}

	@GetMapping("/jpa/products/{productname}")
	public Product getProduct(@PathVariable String productname){
		return productJpaRepository.findByProductname(productname);
		//return productService.findById(id);
	}
	
}
