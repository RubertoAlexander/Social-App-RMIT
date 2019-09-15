package com.sept.rest.webservices.restfulwebservices.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sept.rest.webservices.restfulwebservices.jwt.JwtInMemoryUserDetailsService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class RegistorJpaResource {
	
	@Autowired
	private UserService userSerivce;
	
	@Autowired
	private JwtInMemoryUserDetailsService userDetailsService;

	@Autowired
	private UserJpaRepository UserJpaRepository;
	
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public RegistorJpaResource(UserJpaRepository UserJpaRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.UserJpaRepository = UserJpaRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public String signUp(@RequestBody NewUser user) {
    	NewUser exists = UserJpaRepository.findByUsername(user.getUsername());
    	if(exists != null) {
    		return "user already exists";
    	} else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            UserJpaRepository.save(user);
            return "user created";
    	}

        
    }
	
		
}
