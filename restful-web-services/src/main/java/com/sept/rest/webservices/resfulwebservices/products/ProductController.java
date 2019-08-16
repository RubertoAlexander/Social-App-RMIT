package com.sept.rest.webservices.resfulwebservices.products;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

public class ProductController {
	
	@Autowired
	private ProductService productService;

	@GetMapping(value = "/getAllProducts")
    public @NotNull Iterable<Product> getAllProducts() {
        return productService.getAllProducts();
    }
	
	@GetMapping(value = "/getProduct")
	public @NotNull Product getProduct(Long id) {
		return productService.getProduct(id);
	}
	
}
