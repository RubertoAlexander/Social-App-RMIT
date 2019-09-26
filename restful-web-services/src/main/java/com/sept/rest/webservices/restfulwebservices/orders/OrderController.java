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

import com.sept.rest.webservices.restfulwebservices.lineitem.LineItem;
import com.sept.rest.webservices.restfulwebservices.lineitem.LineItemService;
import com.sept.rest.webservices.restfulwebservices.products.Product;
import com.sept.rest.webservices.restfulwebservices.products.ProductService;
import com.sept.rest.webservices.restfulwebservices.register.NewUser;

@CrossOrigin(origins = "*")
@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private LineItemService lineItemService;
	
	@Autowired
	private ProductService productService;
	
	@PostMapping(value = "/api/orders/{userName}")
	@ResponseBody
	public ResponseEntity<OrderResponse> create(@PathVariable String userName, @RequestBody Long[] products_id) {
		
		Order order;
		NewUser user;
		
		if (orderService.userExist(userName)) {
			order = new Order();
			user = orderService.findUser(userName);
			order.setUser(user);
		} else {
			return new ResponseEntity<>(new OrderResponse(null, "User does not exist. Order creation failed."),
					HttpStatus.NOT_ACCEPTABLE);
		}
		
		orderService.create(order);
		
		for (Long product_id : products_id) {
			Product product;
			LineItem item;
			if (productService.productExist(product_id)) {
				product = productService.findProduct(product_id);
				if (product.isStatus()) {
					product.setStatus(false);
					item = new LineItem();
					item.setOrder(order);
					item.setProduct(product);
					lineItemService.create(item);
					order.getLineItems().add(item);
				}
			}
		}
		
		if (user.getCashBalance() >= order.getTotalPrice()) {
			user.setCashBalance(user.getCashBalance() - order.getTotalPrice());
			order.setPaid(true);
			orderService.update(order);
			return new ResponseEntity<>(new OrderResponse(order, "Order Created and Paid"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new OrderResponse(order, "Insufficient Funds"), HttpStatus.NOT_ACCEPTABLE);
		}

	}

}
