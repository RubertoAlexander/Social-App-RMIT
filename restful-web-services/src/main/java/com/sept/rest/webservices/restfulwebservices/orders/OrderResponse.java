package com.sept.rest.webservices.restfulwebservices.orders;

public class OrderResponse {
	
	private Order order;
	
	private String message;
	
	public OrderResponse(Order order, String message) {
		this.order = order;
		this.message = message;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	
}
