package com.sept.rest.webservices.restfulwebservices.products;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import com.sept.rest.webservices.restfulwebservices.user.UserService;

@CrossOrigin(origins = "*")
@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;

	@GetMapping("/jpa/products/all")
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

	@GetMapping("/jpa/products/name/{productName}")
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
	
	@GetMapping("/jpa/products/search/{keyword}")
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

	@RequestMapping(value = "/jpa/products/sell/{user_id}", method =  RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	@ResponseBody
	public ResponseEntity<Object> sellProduct(@PathVariable String user_id, @RequestPart("file") MultipartFile file, @RequestParam("Documemt") Product product) {
		Product product1 = new Product();
		try {
			product1.setImage(file.getBytes());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		System.out.println(user_id);
		System.out.println(product);
		
		//Long user_id_new = Long.parseLong(user_id);
//		userService.findById(user_id_new).getProducts().add(product);
//		product.setUser(userService.findById(user_id_new));
//		productService.saveProduct(product);
		
//		return new ResponseEntity<>(product, HttpStatus.CREATED);
		return null;
	}

	@GetMapping("/products/id/{id}")
	public Product getProductByID(@PathVariable Long id) {
		return productService.findById(id);
	}

}
