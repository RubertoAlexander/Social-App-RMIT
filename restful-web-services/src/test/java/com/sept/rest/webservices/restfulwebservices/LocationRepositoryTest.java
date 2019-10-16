package com.sept.rest.webservices.restfulwebservices;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import com.sept.rest.webservices.restfulwebservices.location.Location;
import com.sept.rest.webservices.restfulwebservices.location.LocationJpaRepository;
import com.sept.rest.webservices.restfulwebservices.location.LocationService;
import com.sept.rest.webservices.restfulwebservices.products.Product;
import com.sept.rest.webservices.restfulwebservices.products.ProductJpaRepository;
import com.sept.rest.webservices.restfulwebservices.user.UserJpaRepository;
import com.sept.rest.webservices.restfulwebservices.user.UserService;

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
public class LocationRepositoryTest {
	
	@Autowired
	private LocationJpaRepository locationRepository;
	
	@Autowired
	private UserJpaRepository userService;
	
	private static Validator validator;
	private int expected = 0;
	
	Location locationValid;
	Location locationInvalid;
	// number of locations in database before adding
	int numOfLocations;
	
	@Before
	public void setup() {
		
		validator = Validation.buildDefaultValidatorFactory().getValidator();
		// create new location valid
		locationValid = new Location();
		locationValid.setLocation("124.12312332, -121.2123123");
		locationValid.setUserId(1231L);
		locationValid.setDate(1231412412L);
		// set original size of table
		numOfLocations = locationRepository.findAll().size();
		// create invalid location
		locationInvalid = new Location();
		locationInvalid.setLocation(" ");
		locationInvalid.setUserId(123L);
		
	}
	
	@Test
	public void addLocationValid() {
		locationRepository.save(locationValid);
		Location testLocation = locationRepository.findLocationByUserId(1231L);
		assertTrue(testLocation.getLocation().equals(locationValid.getLocation()));

	}
	
	// test to see if location entry can be set as blank violations will be added to list
	// if violation detected i.e. to check for violation see if the vioaltion list is > 0
	@Test
	public void locationStringInValid() {
		Location locationMissingStrings = new Location();
		locationMissingStrings.setLocation("");
		Set<ConstraintViolation<Location>> violations = validator.validate(locationInvalid);

		assertThat(violations.size()).isGreaterThan(0);
	}
	
	@Test
	public void locationStringValid() {
		Set<ConstraintViolation<Location>> violations = validator.validate(locationValid);

		assertThat(violations.size()).isEqualTo(expected);
	}
	// user exists
	@Ignore
	@Test
	public void userExistInLocation() {
		assertTrue(userService.existsById(locationValid.getUserId()));
		
	}
	// user doesn't exist
	@Test
	public void userDoesntExistInLocation() {
		Long userId = (long) 123;
		assertFalse(userService.existsById(userId));
	}
	
	// check all feilds are set
	@Test
	public void check_all_location_feilds_valid() {
		Set<ConstraintViolation<Location>> violations = validator.validate(locationValid);

		assertThat(violations.size()).isEqualTo(expected);
	}
	
	// check if one feild isn't set
	
	
	// added one location to locations data base so one should exist
	@Ignore
	@Test
	public void getAllLocations() {
		List<Location> foundLocations = locationRepository.findAll();
		System.out.println(foundLocations.size());
		assertThat(foundLocations.size()).isEqualTo(numOfLocations + 1);
	}
	
	
	
	
	
	
	
	
	
	
	

}
