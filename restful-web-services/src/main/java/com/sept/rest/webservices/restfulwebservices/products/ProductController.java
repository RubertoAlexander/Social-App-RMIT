package com.sept.rest.webservices.restfulwebservices.products;

import com.sept.rest.webservices.restfulwebservices.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ProductController {

	private final ProductService productService;
	private final UserService userService;

	@Autowired
	public ProductController(ProductService productService, UserService userService) {
		this.productService = productService;
		this.userService = userService;
	}

	@GetMapping("/jpa/products/all")
	public ResponseEntity<Object> getAllProducts() {
		HttpStatus httpStatus;
		List<Product> products = productService.findAll();
		if (products.size() > 0) {
			httpStatus = HttpStatus.OK;
		} else {
			httpStatus = HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<>(products, httpStatus);
	}

	@GetMapping("/jpa/products/name/{productName}")
	public ResponseEntity<Object> getProductByName(@PathVariable String productName) {
		HttpStatus httpStatus;
		List<Product> products = productService.findByProductName(productName);
		if (products.size() > 0) {
			httpStatus = HttpStatus.OK;
		} else {
			httpStatus = HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<>(products, httpStatus);
	}
	
	@GetMapping("/jpa/products/search/{keyword}")
	public ResponseEntity<Object> getProductByKeyword(@PathVariable String keyword) {
		HttpStatus httpStatus = HttpStatus.NO_CONTENT;
		List<Product> productsFound = new ArrayList<>();
		for (Product product : productService.findAll()) {
			if (product.getProductName().toLowerCase().contains(keyword.toLowerCase())) {
				productsFound.add(product);
				httpStatus = HttpStatus.OK;
			}
		}
		return new ResponseEntity<>(productsFound, httpStatus);
	}

	@PostMapping("/jpa/products/sell/{user_id}")
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
