package com.sept.rest.webservices.restfulwebservices.orders;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sept.rest.webservices.restfulwebservices.register.NewUser;
import com.sept.rest.webservices.restfulwebservices.register.UserJpaRepository;

@Service
public class OrderService {

	@Autowired
	private OrderJpaRepository orderRepository;

	@Autowired
	private UserJpaRepository userRepository;

	public List<Order> findAll() {
		return this.orderRepository.findAll();
	}

	public Order create(Order order) {
		order.setDate(LocalDate.now());
		return this.orderRepository.save(order);
	}

	public void update(Order order) {
		this.orderRepository.save(order);
	}
	
	/* USER SERVICE METHODS */
	
	public NewUser findUser(String user) {
		NewUser optional = this.userRepository.findByUsername(user);

		return optional;
	}
	
	public boolean userExist(String user) {
		NewUser newuser = this.userRepository.findByUsername(user);
		if(newuser != null) {
			return true;
		}
		return false;

	}

}
