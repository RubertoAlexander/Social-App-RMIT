package com.sept.rest.webservices.restfulwebservices.register;

import com.sept.rest.webservices.restfulwebservices.jwt.JwtInMemoryUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class RegistorJpaResource {

  private long count = 0;

  private final UserService userService;
  private final JwtInMemoryUserDetailsService userDetailsService;
  private final UserJpaRepository UserJpaRepository;
  private final PasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public RegistorJpaResource(
      UserService userService, JwtInMemoryUserDetailsService userDetailsService,
      UserJpaRepository UserJpaRepository, PasswordEncoder bCryptPasswordEncoder) {
    this.userService = userService;
    this.userDetailsService = userDetailsService;
    this.UserJpaRepository = UserJpaRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }


  @PostMapping("/sign-up")
  public String signUp(@RequestBody NewUser user) {
    NewUser exists = UserJpaRepository.findByUsername(user.getUsername());
    if (exists != null) {
      return "user already exists";
    } else {
      user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      user.setId(count++);
      UserJpaRepository.save(user);
      return "user created";
    }
  }

}
