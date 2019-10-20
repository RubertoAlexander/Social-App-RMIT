package com.sept.rest.webservices.restfulwebservices.favourites;

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

import com.sept.rest.webservices.restfulwebservices.location.Location;
import com.sept.rest.webservices.restfulwebservices.products.Product;
import com.sept.rest.webservices.restfulwebservices.products.ProductService;
import com.sept.rest.webservices.restfulwebservices.user.UserService;

@CrossOrigin(origins = "*")
@RestController
public class FavouriteController {
	
	@Autowired
	private FavouriteService favouriteService;
	
	@Autowired
	private UserService userService;
	
	
	// add favourite
	@PostMapping("/favourite/add/{user_id}")
	@ResponseBody
	public String addFavourite(@PathVariable Long user_id, @RequestBody Favourite favourite) {
		if(userService.existsById(user_id)) {
			favouriteService.saveFavourite(favourite);
			return "succuess, favourite added";
			
		} else {
			return "user doesnt exist";
			
		}
	}
	
	
	// find by user
	
	// get users location
	
	@GetMapping("/get/favourite/{user_id}")
	@ResponseBody
	public List<Favourite> getLocation(@PathVariable String user_id ) {
		List<Favourite> favourite  = favouriteService.findByUser(Long.parseLong(user_id));
		return favourite;
	}	
	
	// find all favourites
	
	@GetMapping("/get/favourite/all")
	@ResponseBody
	public List<Favourite> getLocation() {
		List<Favourite> favourite  = favouriteService.findAllFavs();
		return favourite;
	}	

}
