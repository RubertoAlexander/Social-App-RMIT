package com.sept.rest.webservices.restfulwebservices;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import com.sept.rest.webservices.restfulwebservices.products.Product;
import com.sept.rest.webservices.restfulwebservices.products.ProductJpaRepository;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class ProductRepositoryTest {

	private Product product;
	private Product product2;
	private static Validator validator;

	// managers the USER table
	@Autowired
	private ProductJpaRepository productRepository;

	@BeforeClass
	public static void executeBeforeAll() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	@Before
	public void executeBeforeEach() {
		productRepository.save(new Product("Programmers Guide", 12.00, "blablablablabla"));
		productRepository.save(new Product("Elephant Book", 13.50, "blablablablabla"));
		productRepository.save(new Product("Self Taught Programmer", 11.00, "blablablablabla"));
		productRepository.save(new Product("Computer Science Book", 10.50, "blablablablabla"));

		product = new Product("Beginning Programming Reference for dummies", 13.00, "blablablablabla");
		product2 = new Product("Computer Science Distilled", 14.00, "blablablablabla");
	}

	@Test
	public void productNameBlankInvalid() {
		product.setProductName(" ");
		Set<ConstraintViolation<Product>> violations = validator.validate(product);

		assertThat(violations.size()).isEqualTo(1);
	}

	@Test
	public void priceLessThanZeroInvalid() {
		product.setPrice(-10.10);
		Set<ConstraintViolation<Product>> violations = validator.validate(product);

		assertThat(violations.size()).isEqualTo(1);
	}

	@Test
	public void descriptionBlankInvalid() {
		product.setDescription(" ");
		Set<ConstraintViolation<Product>> violations = validator.validate(product);

		assertThat(violations.size()).isEqualTo(0);
	}

	@Ignore
	@Test
	public void getProductByName() {
		// given
		productRepository.save(product);

		// when
		Product foundProduct = productRepository.findByProductName(product.getProductName()).get(0);

		// then
		assertThat(foundProduct.getProductName()).isEqualTo(product.getProductName());
	}

	@Ignore
	@Test
	public void insertTwoSameProducts() {
		productRepository.save(product);
		Product copyOfProduct = new Product(product.getProductName(), product.getPrice(),product.getDescription());
		productRepository.save(copyOfProduct);

		List<Product> foundProducts = productRepository.findByProductName("Beginning Programming Reference for dummies");

		assertThat(foundProducts.size() == 2);
		assertThat(foundProducts.get(0).getId()).isNotEqualTo(foundProducts.get(1).getId());
	}

    //TODO this test will not work when we add in mock data
    @Ignore
	@Test
	public void getAllInitialProducts() {
		List<Product> foundProducts = productRepository.findAll();
		assertThat(foundProducts.size()).isEqualTo(4);
	}

    //TODO this test will not work when we add in mock data
    @Ignore
	@Test
	public void getAllProductsWithAddedProducts() {
		// given
		productRepository.save(product);
		productRepository.save(product2);

		// when
		List<Product> foundProducts = productRepository.findAll();

		// then
		assertThat(foundProducts.size()).isEqualTo(6);
		assertThat(foundProducts.get(4)).isEqualTo(product);
		assertThat(foundProducts.get(5)).isEqualTo(product2);
	}

}
