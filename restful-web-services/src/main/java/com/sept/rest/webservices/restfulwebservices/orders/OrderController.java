package com.sept.rest.webservices.restfulwebservices.orders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping(value = "/api/orders/{userID}")
	@ResponseBody
	public ResponseEntity<OrderResponse> create(@PathVariable long userID, @RequestBody List<Long> productsID) {
		
		Order order = new Order();
		if (orderService.getUser(userID) != null) {
			System.out.println(orderService.getUser(userID).getCashBalance());
			if (orderService.getUser(userID).getCashBalance() == 0) {
				orderService.getUser(userID).setCashBalance(100);
			}
			order.setUser(orderService.getUser(userID));
		} else {
			return new ResponseEntity<>(new OrderResponse(order, "User does not exist. Order creation failed."),
					HttpStatus.NOT_ACCEPTABLE);
		}
		
		for (Long productID : productsID) {
			if (orderService.getProducts(productID) != null && orderService.getProducts(productID).isStatus()) {
				orderService.getProducts(productID).setStatus(false);
				order.getProducts().add(orderService.getProducts(productID));
			}
		}
		
		if (order.getUser().getCashBalance() >= order.getTotalPrice()) {
			order.getUser().setCashBalance(order.getUser().getCashBalance() - order.getTotalPrice());
			order.setPaid(true);
			orderService.create(order);
			return new ResponseEntity<>(new OrderResponse(order, "Order Created and Paid"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new OrderResponse(order, "Insufficient Funds"), HttpStatus.NOT_ACCEPTABLE);
		}

	}

}
