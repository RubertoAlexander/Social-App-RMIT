package com.sept.rest.webservices.restfulwebservices;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class OrderServiceTest {
	
	@Test
	public void findAllInitalOrders() {
		
	}
	
	@Test
	public void findOrderById() {
		
	}
	
	@Test
	public void findOrderByIdException() {
		
	}
	
	@Test
	public void createOrder() {
		
	}
	
	@Test
	public void createOrderException() {
		
	}
	
	@Test
	public void deleteOrderById() {
		
	}
	
	@Test
	public void deleteOrderByIdException() {
		
	}
	

}
