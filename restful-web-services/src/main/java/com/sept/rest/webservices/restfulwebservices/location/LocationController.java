package com.sept.rest.webservices.restfulwebservices.location;

import com.sept.rest.webservices.restfulwebservices.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	
	// get all users locations
	
	@GetMapping("/get/location/all")
	@ResponseBody
	public List<Location> getAllLocations() {
		List<Location> location  = locationService.getAllLocations();
		return location;
	}
	
	
	
	
	

}
