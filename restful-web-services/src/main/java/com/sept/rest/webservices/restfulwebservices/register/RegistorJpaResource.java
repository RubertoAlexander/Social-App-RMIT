package com.sept.rest.webservices.restfulwebservices.register;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sept.rest.webservices.restfulwebservices.jwt.JwtInMemoryUserDetailsService;
import com.sept.rest.webservices.restfulwebservices.todo.Todo;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class RegistorJpaResource {
	
	@Autowired
	private UserService userSerivce;
	private long count = 0;
	
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
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setId(count++);
        UserJpaRepository.save(user);
        return "user created";
        
    }
	
		
}
