package com.sept.rest.webservices.restfulwebservices.register;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserJpaRepository userRepository;
	
	public NewUser create(NewUser user) {
		return userRepository.save(user);
	}
	
	public NewUser findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public boolean existsById(Long id) {
		return userRepository.existsById(id);
	}

	public NewUser findById(Long id) {
		Optional<NewUser> optional = this.userRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}


}
