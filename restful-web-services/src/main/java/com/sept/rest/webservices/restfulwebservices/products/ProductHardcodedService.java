package com.sept.rest.webservices.restfulwebservices.products;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProductHardcodedService {
	
	private static List<Product> products = new ArrayList<>();
	private static long idCounter = 0;

	static {
		products.add(new Product(++idCounter, "Programmers Guide", 12.00 , "blablablabla"));
		products.add(new Product(++idCounter, "Elephant Book", 13.20, "blablablabla"));
		products.add(new Product(++idCounter, "Self taught programmer", 15.60, "blablablabla"));
	}

	public List<Product> findAll() {
		return products;
	}
	
	public Product findById(long id) {
		for (Product product : products) {
			if (product.getId() == id) {
				return product;
			}
		}

		return null;
	}

}
