package com.sept.rest.webservices.restfulwebservices;

import com.sept.rest.webservices.restfulwebservices.classes.ClassJpaRepository;
import com.sept.rest.webservices.restfulwebservices.classes.ClassUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class ClassesRepositoryTest {
	
	static ClassUser validClass;
	static ClassUser invalidClass;
	private static Validator validator;
	
	@Autowired
	private ClassJpaRepository classesRepository;
	
	@Before
	public void setup() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();

		// create valid class
		validClass = new ClassUser(123L, "test_name", "description_test", "123.312 , -123.2312");
		invalidClass = new ClassUser();
	}
	
	// check all fields are constainted correctly and are full
	@Test
	public void testAllFieldsExists() {
		Set<ConstraintViolation<ClassUser>> violations = validator.validate(validClass);

		assertThat(violations.size()).isEqualTo(0);
	}
	
	@Test
	public void testAllFieldsDoNotExist() {
		Set<ConstraintViolation<ClassUser>> violations = validator.validate(invalidClass);

		assertThat(violations.size()).isGreaterThan(0);
	}
	
	@Test
	public void addClassValid() {
		classesRepository.save(validClass);
		List<ClassUser> testClass = classesRepository.findClassesByUser(validClass.getUser());
		assertEquals(testClass.get(0).getUser(), (validClass.getUser()));
	}
	
	
	
	
	
	
	

}