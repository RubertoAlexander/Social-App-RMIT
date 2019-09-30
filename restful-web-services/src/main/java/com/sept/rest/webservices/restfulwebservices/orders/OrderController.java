package com.sept.rest.webservices.restfulwebservices.orders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sept.rest.webservices.restfulwebservices.lineitem.LineItem;
import com.sept.rest.webservices.restfulwebservices.products.Product;
import com.sept.rest.webservices.restfulwebservices.products.ProductService;
import com.sept.rest.webservices.restfulwebservices.user.User;
import com.sept.rest.webservices.restfulwebservices.user.UserService;

@CrossOrigin(origins = "*")
@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@PostMapping(value = "/api/orders/{user_id}")
	@ResponseBody
	public ResponseEntity<Order> create(@PathVariable Long user_id, @RequestBody List<Long> products_id) {
		
		Order order = new Order();
		User user = userService.findById(user_id);
		user.getOrders().add(order);
		order.setUser(user);
		
		for (Long product_id : products_id) {
			Product product;
			LineItem item;
			
			product = productService.findById(product_id);
			if (productService.productSold(product)) {
				item = new LineItem();
				item.setProduct(product);
				order.getLineItems().add(item);
				item.setOrder(order);
			}
		}
		
		for (LineItem item: order.getLineItems()) {
			item.getProduct().setStatus(false);
		}
		
		userService.cashTransaction(user.getId(), order);
		orderService.create(order);
		
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/api/orders/remove/{order_id}")
	public ResponseEntity<Object> delete(@PathVariable Long order_id) {
		orderService.deleteById(order_id);
		return new ResponseEntity<>("The order with id " + order_id + " has been successfully deleted.", HttpStatus.OK);
	}

}
