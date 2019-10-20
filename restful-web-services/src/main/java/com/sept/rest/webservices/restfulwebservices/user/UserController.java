package com.sept.rest.webservices.restfulwebservices.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sept.rest.webservices.restfulwebservices.orders.Order;
import com.sept.rest.webservices.restfulwebservices.products.Product;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
	private final UserService userService;
	private final PasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UserController(UserService userService, PasswordEncoder bCryptPasswordEncoder) {
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@PostMapping("/sign-up")
	public ResponseEntity<String> signUp(@RequestBody User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userService.create(user);
		return new ResponseEntity<>("User Created", HttpStatus.CREATED);
	}
	
	@GetMapping("user/{id}")
	public User getUserById(@PathVariable Long id) {
		return userService.findById(id);
	}

	@PostMapping("/user/{id}/cashBalance/add/{amount}")
	public User addCashBalance(@PathVariable double amount, @PathVariable Long id) {
		User user = userService.findById(id);
		user.setCashBalance(user.getCashBalance() + amount);
		userService.update(user);
		return user;
	}
	
	@GetMapping("/user/{id}/products")
	public List<Product> getSellingProducts(@PathVariable Long id) {
		return userService.findById(id).getProducts();
	}
	
	@GetMapping("/user/{id}/orders")
	public List<Order> getPastOrders(@PathVariable Long id) {
		return userService.findById(id).getOrders();
	}
	
	@GetMapping("/user/id/{username}") 
	public Long getUserIdByUsername(@PathVariable String username) {
		return userService.findByUsername(username).getId();
	}
	

}
