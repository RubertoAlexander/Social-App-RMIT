package com.sept.rest.webservices.restfulwebservices.products;

import com.sept.rest.webservices.restfulwebservices.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import com.sept.rest.webservices.restfulwebservices.dbfile.DBFile;
import com.sept.rest.webservices.restfulwebservices.dbfile.DBFileService;
import com.sept.rest.webservices.restfulwebservices.user.UserService;

@CrossOrigin(origins = "*")
@RestController
public class ProductController {

	private final ProductService productService;
	private final UserService userService;
    private final DBFileService DBFileService;

	@Autowired
	public ProductController(ProductService productService, UserService userService, DBFileService DBFileService) {
		this.productService = productService;
		this.userService = userService;
		this.DBFileService = DBFileService;
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

	@GetMapping("/jpa/products/id/{product_id}")
	public ResponseEntity<Object> getProductByID(@PathVariable Long product_id) {
		return new ResponseEntity<>(productService.findById(product_id), HttpStatus.OK);
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
	public ResponseEntity<Object> sellProduct(@PathVariable Long user_id,
										@RequestParam("productName") String productName,
										@RequestParam("price") double price,
										@RequestParam("description") String description,
										@RequestParam("file") MultipartFile file) {
		Product product = new Product(productName, price, description, userService.findById(user_id));
		userService.findById(user_id).getProducts().add(product);
		productService.saveProduct(product);
		DBFile dbFile = DBFileService.storeFile(file, product);
		product.setPicture(dbFile);


		return new ResponseEntity<>(product, HttpStatus.CREATED);
	}
}
