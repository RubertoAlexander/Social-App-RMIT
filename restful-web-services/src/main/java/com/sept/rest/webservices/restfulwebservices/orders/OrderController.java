package com.sept.rest.webservices.restfulwebservices.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sept.rest.webservices.restfulwebservices.products.Product;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping(value = "/api/orders")
	@ResponseBody
	public ResponseEntity<OrderResponse> create(@RequestBody OrderForm orderForm) {
		Order order = new Order();
		order.setUser(orderForm.getUser());
		orderService.create(order);
		
		for (Product product : orderForm.getProducts()) {
			if (product.isStatus()) {
				product.setStatus(false);
				order.getProducts().add(product);
			}
		}
		orderService.update(order);
		
		if (orderForm.getUser().getCashBalance() < order.getTotalPrice()) {
			return new ResponseEntity<>(new OrderResponse(order, "Insufficient Funds"), HttpStatus.NOT_ACCEPTABLE);
		}
		order.getUser().setCashBalance(order.getUser().getCashBalance() - order.getTotalPrice());
		order.setPaid(true);
	    
	    return new ResponseEntity<>(new OrderResponse(order, "Order Created and Paid"), HttpStatus.OK);
	}

}
