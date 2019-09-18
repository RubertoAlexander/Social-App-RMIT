package com.sept.rest.webservices.restfulwebservices.orders;

import java.time.LocalDate;
import java.util.Optional;

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
		Optional<Product> optional = this.productJpaRepository.findById(id);
		if (optional.isPresent()) {
			System.out.println(id);
			return optional.get();
		}
		return null;
	}

	public NewUser getUser(Long id) {
		Optional<NewUser> optional = this.userJpaRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

}
