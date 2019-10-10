package com.sept.rest.webservices.restfulwebservices;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.sept.rest.webservices.restfulwebservices.exceptions.DataDuplicationException;
import com.sept.rest.webservices.restfulwebservices.exceptions.DataNotFoundException;
import com.sept.rest.webservices.restfulwebservices.lineitem.LineItem;
import com.sept.rest.webservices.restfulwebservices.orders.Order;
import com.sept.rest.webservices.restfulwebservices.products.Product;
import com.sept.rest.webservices.restfulwebservices.products.ProductJpaRepository;
import com.sept.rest.webservices.restfulwebservices.user.User;
import com.sept.rest.webservices.restfulwebservices.user.UserJpaRepository;
import com.sept.rest.webservices.restfulwebservices.user.UserService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class UserServiceTest {
	
	private User user_j;
	private Product book_PG;
	private Product book_EB;
	
	@TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
  
        @Bean
        public UserService userService() {
            return new UserService();
        }
        
    }
	
	@Autowired
	private ProductJpaRepository productRepository;
	
	@Autowired
	private UserJpaRepository userRepository;
	
	@Autowired 
	private UserService userService;
	
	@Before
	public void beforeEach() {
		userRepository.deleteAll();
		user_j = new User("Jack", "Password");
		userRepository.save(user_j);
	}
	
	@Test
	public void signUp() {
		User user_z = new User("Zac", "Password");
		userService.create(user_z);
		
		assertThat(userService.findByUsername("Zac")).isEqualTo(user_z);
	}
	
	@Test
	public void signUpWithDuplicatedName() {
		try {
			userService.create(user_j);
			fail();
		} catch (Exception ex) {
			assertTrue(ex instanceof DataDuplicationException);
		}
	}
	
	@Test
	public void findUserByUsername() {
		try {
			userService.findByUsername(user_j.getUsername());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Test
	public void findUserByUsernameException() {
		String random_username = "Tom";
		try {
			userService.findByUsername(random_username);
			fail();
		} catch (Exception ex) {
			assertTrue(ex instanceof UsernameNotFoundException);
		}
	}
	
	@Test
	public void findUserById() {
		try {
			userService.findById(user_j.getId());
		} catch (Exception ex) {
			fail();
		}
	}
	
	@Test
	public void findUserByIdException() {
		Long random_id = (long) 0;
		try {
			userService.findById(random_id);
			fail();
		} catch (Exception ex) {
			assertTrue(ex instanceof DataNotFoundException);
		}
	}
	
	@Test
	public void cashTransaction() {
		book_PG = new Product("Programmers Guide", 12.00, "blablablablabla", user_j);
		book_EB = new Product("Elephant Book", 13.50, "blablablablabla", user_j);
		
		productRepository.save(book_PG);
		productRepository.save(book_EB);
		
		User user_z = new User("Zac", "Password");
		user_z.setCashBalance(100);
		userService.create(user_z);
		
		Order order = new Order();
		user_z.getOrders().add(order);
		order.setUser(user_z);
		
		LineItem item;
		item = new LineItem();
		item.setProduct(book_PG);
		item.setOrder(order);
		order.getLineItems().add(item);
		
		item = new LineItem();
		item.setProduct(book_EB);
		item.setOrder(order);
		order.getLineItems().add(item);
		
		userService.cashTransaction(user_z.getId(), order);
		
		assertThat(user_z.getCashBalance()).isEqualTo(100 - order.getTotalPrice());
	}
	
	@Ignore
	@Test
	public void cashTransactionException() {
		
	}

}
