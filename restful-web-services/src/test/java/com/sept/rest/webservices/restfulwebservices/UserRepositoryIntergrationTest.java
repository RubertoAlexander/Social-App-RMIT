package com.sept.rest.webservices.restfulwebservices;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.sept.rest.webservices.restfulwebservices.register.NewUser;
import com.sept.rest.webservices.restfulwebservices.register.UserJpaRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntergrationTest {
	
		// managers the database
	   @Autowired
	    private TestEntityManager entityManager;
	 
	   	// managers the USER table
	   @Autowired
	    private UserJpaRepository userRepository;
	   
	   /*
	    * 	In the below test we check if we can first insert user into database and use the
	    * 	available API call findByUsername to retrieve the user
	    */
	   @Test
	   public void whenFindByUserName_thenReturnUser() {
	       // given
	       NewUser user = new NewUser("john", "password");
	       entityManager.persist(user);
	       entityManager.flush();
	    
	       // when
	       NewUser found = userRepository.findByUsername(user.getUsername());
	    
	       // then
	       assertThat(found.getUsername())
	         .isEqualTo(user.getUsername());
	   }
	

}
