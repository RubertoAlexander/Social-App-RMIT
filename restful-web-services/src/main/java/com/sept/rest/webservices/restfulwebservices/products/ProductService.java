package com.sept.rest.webservices.restfulwebservices.products;

public interface ProductService {
	
	public Iterable<Product> getAllProducts();
	
	public Product getProduct(long id);
	
	public Product save(Product product);
}
