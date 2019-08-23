package com.sept.rest.webservices.restfulwebservices.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProductResource {

	@Autowired
	private ProductHardcodedService productService;

	@GetMapping("/products/all")
	public List<Product> getAllProducts() {
		// Thread.sleep(3000);
		return productService.findAll();
	}
	
	@GetMapping("/products/{id}")
	public Product getProduct(@PathVariable long id) {
		// Thread.sleep(3000);
		return productService.findById(id);
	}
		
}