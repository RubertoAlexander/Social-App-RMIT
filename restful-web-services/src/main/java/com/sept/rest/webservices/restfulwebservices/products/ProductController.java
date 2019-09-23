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

@CrossOrigin(origins="*")
@RestController
public class ProductController {
	
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
	
	@PostMapping(value = "/jpa/products/sell")
	@ResponseBody
	public Product sellProduct(@RequestBody Product product) {
		productJpaRepository.save(product);
		return product;
	}


	@GetMapping("/products/{id}")
	public Product getProductByID(@PathVariable Integer id){
		Product product = productJpaRepository.findById(id);
		if(product != null) {
			return product;
		}
		return null;
	}


}
