package com.sept.rest.webservices.restfulwebservices;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.sept.rest.webservices.restfulwebservices.exceptions.DataNotFoundException;
import com.sept.rest.webservices.restfulwebservices.exceptions.InvalidDataException;
import com.sept.rest.webservices.restfulwebservices.products.Product;
import com.sept.rest.webservices.restfulwebservices.products.ProductJpaRepository;
import com.sept.rest.webservices.restfulwebservices.products.ProductService;
import com.sept.rest.webservices.restfulwebservices.user.User;
import com.sept.rest.webservices.restfulwebservices.user.UserJpaRepository;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class ProductServiceTest {

	private User user;
	private Product book_PG;
	private Product book_EB;
	private Product book_STP;
	private Product book_CSP;
	private Product book_BPRFD;
	private Product book_CSD;
	
	@TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
  
        @Bean
        public ProductService employeeService() {
            return new ProductService();
        }
    }
	
	@Autowired
	private ProductJpaRepository productRepository;
	
	@Autowired
	private UserJpaRepository userRepository;
	
	@Autowired
	private ProductService productService;
	
	@Before
	public void executeBeforeEach() {
		productRepository.deleteAll();
		userRepository.deleteAll();
		
		user = new User("John", "Password");
		
		book_PG = new Product("Programmers Guide", 12.00, "blablablablabla", user);
		book_EB = new Product("Elephant Book", 13.50, "blablablablabla", user);
		book_STP = new Product("Self Taught Programmer", 11.00, "blablablablabla", user);
		book_CSP = new Product("Computer Science Book", 10.50, "blablablablabla", user);
		book_BPRFD = new Product("Beginning Programming Reference for dummies", 13.00, "blablablablabla", user);
		book_CSD = new Product("Computer Science Distilled", 14.00, "blablablablabla", user);
		
		userRepository.save(user);
	}
	
	@Test
	public void productNameBlankInvalid() {
		try {
			productService.saveProduct(new Product("", 10.00, "blablabla", user));
			fail();
		} catch (Exception ex) {
			assertThat(ex instanceof InvalidDataException);
		}
	}
	
	@Test
	public void priceLessThanZeroInvalid() {
		try {
			productService.saveProduct(new Product("Programmer's Guide", -10.00, "blablabla", user));
			fail();
		} catch (Exception ex) {
			assertThat(ex instanceof InvalidDataException);
		}
	}

	@Test
	public void descriptionBlankValid() {
		try {
			book_BPRFD.setDescription(" ");
		} catch (Exception ex) {
			fail();
		}
	}
	
	@Test
	public void getProductByName() {
		// given
		productService.saveProduct(book_BPRFD);

		// when
		Product foundProduct = productRepository.findByProductName(book_BPRFD.getProductName()).get(0);

		// then
		assertThat(foundProduct.getProductName()).isEqualTo(book_BPRFD.getProductName());
	}
	
	@Test
	public void getProductByNameException() {
		try {
			productService.findByProductName("random_name");
		} catch (Exception ex) {
			assertThat(ex instanceof DataNotFoundException);
		}
	}
	
	@Test
	public void getAllInitialProducts() {
		productRepository.save(book_PG);
		productRepository.save(book_EB);
		productRepository.save(book_STP);
		productRepository.save(book_CSP);
		
		List<Product> foundProducts = productService.findAll();
		assertThat(foundProducts.size()).isEqualTo(4);
	}
	
	@Test
	public void getAllProductsWithAddedProducts() {
		productRepository.save(book_PG);
		productRepository.save(book_EB);
		productRepository.save(book_STP);
		productRepository.save(book_CSP);
		
		// given
		productService.saveProduct(book_BPRFD);
		productService.saveProduct(book_CSD);

		// when
		List<Product> foundProducts = productService.findAll();
		
		// then
		assertThat(foundProducts.size()).isEqualTo(6);
		assertThat(foundProducts.get(4)).isEqualTo(book_BPRFD);
		assertThat(foundProducts.get(5)).isEqualTo(book_CSD);
	}

	//TODO productSold test
	@Ignore
	@Test
	public void productSoldBeforeBuying() {
		
	}
	
	//TODO productSold test
	@Ignore
	@Test
	public void productSoldAfterBuying() {
		
	}
}
