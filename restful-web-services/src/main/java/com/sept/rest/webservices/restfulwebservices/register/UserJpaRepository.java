package com.sept.rest.webservices.restfulwebservices.register;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<NewUser, Long>{
	NewUser findByUsername(String username);
}
