package com.sept.rest.webservices.restfulwebservices.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sept.rest.webservices.restfulwebservices.exceptions.InsufficientFundsException;
import com.sept.rest.webservices.restfulwebservices.exceptions.InvalidDataException;
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
		try {
			userRepository.save(user);
		} catch (Exception e) {
			if (user.getUsername() == null || user.getUsername().isEmpty()) {
				throw new InvalidDataException("Please insert a valid username.");
			}
			if (user.getPassword() == null || user.getPassword().isEmpty()) {
				throw new InvalidDataException("Please insert a valid password.");
			}
			throw new InvalidDataException("Data insertion was invalid");
		}
		
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
            throw new UsernameNotFoundException(username);
        }
		return user;
	}
	
	public boolean existsById(Long id) {
		return userRepository.existsById(id);
	}

	public User findById(Long id) {
		Optional<User> optional = this.userRepository.findById(id);
		if (optional.isEmpty()) {
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
