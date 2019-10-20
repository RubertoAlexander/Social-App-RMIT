package com.sept.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sept.rest.webservices.restfulwebservices.orders.Order;
import com.sept.rest.webservices.restfulwebservices.products.Product;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="USERS")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotBlank
	private String username;

    @JsonIgnore
    @NotBlank
    private String password;
	
	@DecimalMin("0.00")
	private double cashBalance;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Product> products;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Order> orders;

	public User() {
		
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
		products = new ArrayList<>();
		orders = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	//bcrypt
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public double getCashBalance() {
		return cashBalance;
	}

	public void setCashBalance(double cashBalance) {
		this.cashBalance = cashBalance;
	}
	
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
}