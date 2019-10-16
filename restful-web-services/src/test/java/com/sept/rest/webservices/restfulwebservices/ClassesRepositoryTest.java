package com.sept.rest.webservices.restfulwebservices;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import com.sept.rest.webservices.restfulwebservices.classes.ClassJpaRepository;
import com.sept.rest.webservices.restfulwebservices.classes.Class_;
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
public class ClassesRepositoryTest {
	
	static Class_ validClass;
	static Class_ invalidClass;
	private static Validator validator;
	
	@Autowired
	private ClassJpaRepository classesRepository;
	
	@Before
	public void setup() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
		
		// create valid class
		validClass = new Class_(123L, "test_name", "description_test", "123.312 , -123.2312");
		invalidClass = new Class_();
		
	}
	
	// check all feilds are constainted correctly and are full
	@Test
	public void checking_all_feilds_exist() {
		Set<ConstraintViolation<Class_>> violations = validator.validate(validClass);

		assertThat(violations.size()).isEqualTo(0);
	}
	
	@Test
	public void checking_all_feilds_dontexist() {
		Set<ConstraintViolation<Class_>> violations = validator.validate(invalidClass);

		assertThat(violations.size()).isGreaterThan(0);
	}
	
	
	
	
	
	
	
	

}
