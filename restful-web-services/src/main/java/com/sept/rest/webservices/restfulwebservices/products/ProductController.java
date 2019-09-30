package com.sept.rest.webservices.restfulwebservices.products;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sept.rest.webservices.restfulwebservices.user.UserService;

@CrossOrigin(origins = "*")
@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;

	@GetMapping("/products/all")
	public ResponseEntity<Object> getAllProducts() {
		HttpStatus httpStatus;
		List<Product> products = productService.findAll();
		if (products.size() > 0) {
			httpStatus = HttpStatus.FOUND;
		} else {
			httpStatus = HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<>(products, httpStatus);
	}

	@GetMapping("/products/name/{productName}")
	public ResponseEntity<Object> getProductByName(@PathVariable String productName) {
		HttpStatus httpStatus;
		List<Product> products = productService.findByProductName(productName);
		if (products.size() > 0) {
			httpStatus = HttpStatus.FOUND;
		} else {
			httpStatus = HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<>(products, httpStatus);
	}
	
	@GetMapping("/products/search/{keyword}")
	public ResponseEntity<Object> getProductByKeyword(@PathVariable String keyword) {
		HttpStatus httpStatus = HttpStatus.NO_CONTENT;
		List<Product> productsFound = new ArrayList<>();
		for (Product product : productService.findAll()) {
			if (product.getProductName().toLowerCase().contains(keyword.toLowerCase())) {
				productsFound.add(product);
				httpStatus = HttpStatus.FOUND;
			}
		}
		return new ResponseEntity<>(productsFound, httpStatus);
	}

	@PostMapping("/products/sell/{user_id}")
	@ResponseBody
	public ResponseEntity<Object> sellProduct(@PathVariable Long user_id, @RequestBody Product product) {
		userService.findById(user_id).getProducts().add(product);
		product.setUser(userService.findById(user_id));
		productService.saveProduct(product);
		return new ResponseEntity<>(product, HttpStatus.CREATED);
	}

	@GetMapping("/products/id/{id}")
	public Product getProductByID(@PathVariable Long id) {
		return productService.findById(id);
	}

}
