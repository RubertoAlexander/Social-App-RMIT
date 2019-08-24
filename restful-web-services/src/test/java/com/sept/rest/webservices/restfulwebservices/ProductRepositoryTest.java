package com.sept.rest.webservices.restfulwebservices;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.sept.rest.webservices.restfulwebservices.products.Product;
import com.sept.rest.webservices.restfulwebservices.products.ProductJpaRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class ProductRepositoryTest {

	// managers the database
	@Autowired
	private TestEntityManager entityManager;

	// managers the USER table
	@Autowired
	private ProductJpaRepository productRepository;

	@Test
	public void getAllProducts() {
		// given
		Product product1 = new Product(10, "Spring Boot Guide", 12.50, "blablablablabla");
		entityManager.persist(product1);
		entityManager.flush();

		Product product2 = new Product(11, "Restful Guide", 13.50, "blablablablabla");
		entityManager.persist(product2);
		entityManager.flush();

		// when
		List<Product> foundProducts = productRepository.findAll();

		// then
		assertThat(foundProducts.size()).isEqualTo(11);
		assertThat(foundProducts.get(9)).isEqualTo(product1);
		assertThat(foundProducts.get(10)).isEqualTo(product2);
	}

	@Test
	public void getProductByName() {
		// given
		Product product = new Product(10, "Spring Boot Guide", 12.50, "blablablablabla");
		entityManager.persist(product);
		entityManager.flush();
		
		// when
		Product foundProduct = productRepository.findByProductname("Spring Boot Guide");

		// then
		assertThat(foundProduct.getProductname()).isEqualTo(product.getProductname());
	}

}
