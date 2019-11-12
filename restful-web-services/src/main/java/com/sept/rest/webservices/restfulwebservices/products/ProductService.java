package com.sept.rest.webservices.restfulwebservices.products;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sept.rest.webservices.restfulwebservices.exceptions.DataNotFoundException;
import com.sept.rest.webservices.restfulwebservices.exceptions.InvalidDataException;
import com.sept.rest.webservices.restfulwebservices.exceptions.ProductSoldException;

@Service
public class ProductService {

	@Autowired
	private ProductJpaRepository productRepository;

	public void saveProduct(Product product) {
		try {
			productRepository.save(product);
		} catch (Exception e) {
			if (product.getProductName() == null || product.getProductName().isEmpty()) {
				throw new InvalidDataException("Please insert a valid product name.");
			}
			if (product.getPrice() == null || product.getPrice() < 0) {
				throw new InvalidDataException("Please insert a valid price.");
			}
			throw new InvalidDataException("Data insertion was invalid");
		}
	}
	
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	public Product findById(Long id) {
		Optional<Product> optional = this.productRepository.findById(id);
		if (!optional.isPresent()) {
			throw new DataNotFoundException("Product with id " + id + " can't be found.");
		}
		return optional.get();
	}

	public List<Product> findByProductName(String productName) {
		List<Product> products = productRepository.findByProductName(productName);
		if (products.size() > 0) {
			return products;
		}
		throw new DataNotFoundException("Product " + productName + " can't be found.");
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
