package com.sept.rest.webservices.restfulwebservices.products;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins="*")
@RestController
public class ProductJpaResource {
	
	private int count = 0;
	
	 private final ProductJpaRepository productJpaRepository;

	  @Autowired
	  public ProductJpaResource(ProductJpaRepository setJpaRepository) {
	    this.productJpaRepository = setJpaRepository;
	  }

	@GetMapping("/jpa/products/all")
	public List<Product> getAllProducts(){
		List<Product> products;
		try {
			products = productJpaRepository.findAll();
			if(products.isEmpty()) {
				return null;
			} 
			return products;
			
		} catch (Exception e) {
			System.out.println("error");
		}
		return null;
	
	}

	@GetMapping("/jpa/products/{productName}")
	public List<Product> getProduct(@PathVariable String productName){
		return productJpaRepository.findByProductName(productName);
	}
	
	
	@GetMapping("/products/{id}")
	public Product getProductByID(@PathVariable Integer id){
		Product product = productJpaRepository.findById(id);
		if(product != null) {
			return product;
		} 
		return null;
	}
	
	@PostMapping("/add/product")
	  public String addProduct(@RequestBody Product newProduct) {
			Product product = new Product("asfaf", 124.0, "afsvsa");
			productJpaRepository.save(product);
			return "added";
		    
	  }

	
}
