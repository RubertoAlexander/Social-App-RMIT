package com.sept.rest.webservices.restfulwebservices.orders;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sept.rest.webservices.restfulwebservices.products.Product;
import com.sept.rest.webservices.restfulwebservices.products.ProductJpaRepository;
import com.sept.rest.webservices.restfulwebservices.register.NewUser;
import com.sept.rest.webservices.restfulwebservices.register.UserJpaRepository;

@Service
public class OrderService {

	@Autowired
	private OrderJpaRepository orderRepository;

	@Autowired
	private ProductJpaRepository productJpaRepository;

	@Autowired
	private UserJpaRepository userJpaRepository;

	public Iterable<Order> getAllOrders() {
		return this.orderRepository.findAll();
	}

	public Order create(Order order) {
		order.setDate(LocalDate.now());
		return this.orderRepository.save(order);
	}

	public void update(Order order) {
		this.orderRepository.save(order);
	}

	public Product getProducts(Long id) {
		for (Product product : productJpaRepository.findAll()) {
			if (product.getId() == id) {
				return product;
			}
		}
		return null;
	}

	public NewUser getUser(Long userID) {
		for (NewUser user : userJpaRepository.findAll()) {
			if (user.getId() == userID) {
				return user;
			}
		}
		return null;
	}

}
