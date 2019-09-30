package com.sept.rest.webservices.restfulwebservices.products;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sept.rest.webservices.restfulwebservices.exceptions.DataNotFoundException;
import com.sept.rest.webservices.restfulwebservices.exceptions.ProductSoldException;

@Service
public class ProductService {

	@Autowired
	private ProductJpaRepository productRepository;

	public void saveProduct(Product product) {
		productRepository.save(product);
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public List<Product> findByProductName(String productName) {
		List<Product> products = productRepository.findByProductName(productName);
		if (products.size() > 0) {
			return products;
		}
		throw new DataNotFoundException("Product " + productName + " can't be found.");
	}

	public Product findById(Long id) {
		Optional<Product> optional = this.productRepository.findById(id);
		if (optional.isEmpty()) {
			throw new DataNotFoundException("Product with id " + id + " can't be found.");
		}
		return optional.get();
	}

	public boolean productExist(Long id) {
		return productRepository.existsById(id);
	}
	
	public boolean productSold(Product product) {
		if (product.isStatus()) {
			return true;
		}
		throw new ProductSoldException("Product with id " + product.getId() + " has already been sold.");
	}
}
