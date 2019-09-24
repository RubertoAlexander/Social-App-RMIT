package com.sept.rest.webservices.restfulwebservices.products;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/jpa/products/all")
	public List<Product> getAllProducts() {
		return productService.findAll();
	}

	@GetMapping("/jpa/products/{productName}")
	public List<Product> getProduct(@PathVariable String productName) {
		return productService.findByProductName(productName);
	}

	@PostMapping(value = "/jpa/products/sell")
	@ResponseBody
	public Product sellProduct(@RequestBody Product product) {
		productService.saveProduct(product);
		return product;
	}

	@GetMapping("/products/{id}")
	public Product getProductByID(@PathVariable Long id) {
		if (productService.productExist(id)) {
			return productService.findById(id);
		}
		return null;
	}

}
