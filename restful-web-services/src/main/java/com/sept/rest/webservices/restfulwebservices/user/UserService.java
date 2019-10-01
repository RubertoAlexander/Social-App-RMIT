package com.sept.rest.webservices.restfulwebservices.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sept.rest.webservices.restfulwebservices.exceptions.InsufficientFundsException;
import com.sept.rest.webservices.restfulwebservices.exceptions.DataDuplicationException;
import com.sept.rest.webservices.restfulwebservices.exceptions.DataNotFoundException;
import com.sept.rest.webservices.restfulwebservices.lineitem.LineItem;
import com.sept.rest.webservices.restfulwebservices.orders.Order;

@Service
public class UserService {
	
	@Autowired
	private UserJpaRepository userRepository;
	
	public void create(User user) {
		if (userRepository.existsByUsername(user.getUsername())) {
			throw new DataDuplicationException("User " + user.getUsername() + " already exist.");
		}
		userRepository.save(user);
	}
	
	public void update(User user) {
		if (!userRepository.existsByUsername(user.getUsername())) {
			throw new DataNotFoundException("User " + user.getId() + " can't be found.");
		} 
		userRepository.save(user);
	}
	
	public User findByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new DataNotFoundException("User " + username + " can't be found.");
		}
		return userRepository.findByUsername(username);
	}
	
	public boolean existsById(Long id) {
		return userRepository.existsById(id);
	}

	public User findById(Long id) {
		Optional<User> optional = this.userRepository.findById(id);
		if (optional.empty() != null) {
			throw new DataNotFoundException("User with id " + id + " can't be found.");
		}
		return optional.get();
	}
	
	public void cashTransaction(Long id, Order order) {
		User user = findById(id);
		if (user.getCashBalance() < order.getTotalPrice()) {
			throw new InsufficientFundsException("Insufficient funds.");
		} else {
			user.setCashBalance(user.getCashBalance() - order.getTotalPrice());
			for (LineItem item : order.getLineItems()) {
				User productOwner = item.getProduct().getUser();
				productOwner.setCashBalance(productOwner.getCashBalance() + item.getProduct().getPrice());
			}
		}
	}


}
