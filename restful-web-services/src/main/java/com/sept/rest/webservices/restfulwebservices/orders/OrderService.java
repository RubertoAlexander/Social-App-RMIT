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
	
	public NewUser findUser(Long id) {
		Optional<NewUser> optional = this.userRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public boolean userExist(Long id) {
		return this.userRepository.existsById(id);
	}

}
