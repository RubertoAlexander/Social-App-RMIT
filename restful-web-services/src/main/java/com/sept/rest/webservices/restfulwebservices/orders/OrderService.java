package com.sept.rest.webservices.restfulwebservices.orders;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sept.rest.webservices.restfulwebservices.exceptions.DataNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderJpaRepository orderRepository;

	public List<Order> findAll() {
		return this.orderRepository.findAll();
	}
	
	public Order findById(Long id) {
		Optional<Order> optional = orderRepository.findById(id);
		if (!optional.isPresent()) {
			throw new DataNotFoundException("Order with id " + id + " is not found.");
		}
		return optional.get();
	}

	public Order create(Order order) {
		order.setDate(LocalDate.now());
		return this.orderRepository.save(order);
	}

	public void update(Order order) {
		if (orderRepository.existsById(order.getId())) {
			this.orderRepository.save(order);
		}
		throw new DataNotFoundException("Order with id " + order.getId() + " can't be found.");
	}
	
	public void deleteById(Long id) {
		if (orderRepository.existsById(id)) {
			this.orderRepository.deleteById(id);
			return;
		}
		throw new DataNotFoundException("Order with id " + id + " can't be found.");
	}

}
