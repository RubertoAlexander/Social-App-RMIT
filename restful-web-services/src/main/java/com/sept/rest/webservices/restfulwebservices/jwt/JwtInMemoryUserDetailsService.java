package com.sept.rest.webservices.restfulwebservices.jwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sept.rest.webservices.restfulwebservices.register.NewUser;
import com.sept.rest.webservices.restfulwebservices.register.UserJpaRepository;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

  static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

    private UserJpaRepository userJpaRepository;

    public JwtInMemoryUserDetailsService(UserJpaRepository userJpaRepository) {
  	  this.userJpaRepository = userJpaRepository;
    }

  static {
    inMemoryUserList.add(new JwtUserDetails(1L, "sept",
        "$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "ROLE_USER_2"));
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NewUser newUser = userJpaRepository.findByUsername(username);
        if (newUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new JwtUserDetails(newUser.getId(), newUser.getUsername(), newUser.getPassword(), "USER_ROLE_2");
    //Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
//        .filter(user -> user.getUsername().equals(username)).findFirst();
//
//    if (!findFirst.isPresent()) {
//      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
//    }
//
//    return findFirst.get();
  }

//  public void addUser(long id, String username, String password, String role) {
//	   inMemoryUserList.add(new JwtUserDetails(id, username,
//			   password, role));
// }
}


