package com.sept.rest.webservices.restfulwebservices.orders;

import java.util.ArrayList;
import java.util.List;

import com.sept.rest.webservices.restfulwebservices.products.Product;
import com.sept.rest.webservices.restfulwebservices.register.NewUser;

public class OrderForm {
	
	private NewUser user;
	
	private List<Product> products = new ArrayList<>();

	public NewUser getUser() {
		return user;
	}

	public void setUser(NewUser user) {
		this.user = user;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
