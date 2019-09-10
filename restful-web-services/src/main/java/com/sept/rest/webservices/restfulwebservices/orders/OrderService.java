package com.sept.rest.webservices.restfulwebservices.orders;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	
	@Autowired
	private OrderJpaRepository orderRepository;
	
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

}
