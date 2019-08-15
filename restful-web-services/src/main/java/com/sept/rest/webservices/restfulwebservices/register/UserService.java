package com.sept.rest.webservices.restfulwebservices.register;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sept.rest.webservices.restfulwebservices.todo.Todo;

@Service
public class UserService {
	
	private static List<NewUser> users = new ArrayList<>();
	private static long idCounter = 0;
	
	public boolean addUser(long id, String username, String password) {
		try {
			NewUser user = new NewUser(username,password);
			users.add(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	

}
