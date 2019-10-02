package com.sept.rest.webservices.restfulwebservices.location;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sept.rest.webservices.restfulwebservices.products.Product;
import com.sept.rest.webservices.restfulwebservices.user.UserService;

@CrossOrigin(origins = "*")
@RestController
public class LocationController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LocationService locationService;
	
	//set users location
	
	@PostMapping("/location/set/")
	@ResponseBody
	public String setLocation(@RequestBody Location location) {
		locationService.saveLocation(location);
		
		return "location set";
	}
	
	// get users location
	
	@GetMapping("/get/location/{user_id}")
	@ResponseBody
	public Location getLocation(@PathVariable String user_id ) {
		Location location  = locationService.getLocationOfUser(Long.parseLong(user_id));
		return location;
	}
	
	//delete users location
	
	
	
	
	
	

}
