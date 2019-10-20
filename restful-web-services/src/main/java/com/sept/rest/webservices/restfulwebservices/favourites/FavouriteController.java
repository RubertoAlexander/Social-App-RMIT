package com.sept.rest.webservices.restfulwebservices.favourites;

import com.sept.rest.webservices.restfulwebservices.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class FavouriteController {

    private final FavouriteService favouriteService;
    private final UserService userService;

    @Autowired
	public FavouriteController(FavouriteService favouriteService, UserService userService) {
		this.favouriteService = favouriteService;
		this.userService = userService;
	}

	// add favourite to the database
    @PostMapping("/favourite/add/{user_id}")
    @ResponseBody
    public String addFavourite(@PathVariable Long user_id, @RequestBody Favourite favourite) {
        if (userService.existsById(user_id)) {
            favouriteService.saveFavourite(favourite);
            return "Favourite added successfully";
        } else {
            return "User does not exist";
        }
    }

    // fetch favourites for a user
    @GetMapping("/favourite/{user_id}")
    @ResponseBody
    public List<Favourite> getLocation(@PathVariable String user_id) {
        List<Favourite> favourite = favouriteService.findByUser(Long.parseLong(user_id));
        return favourite;
    }

    // find all favourites
    @GetMapping("/favourite/all")
    @ResponseBody
    public List<Favourite> getLocation() {
        List<Favourite> favourite = favouriteService.findAllFavs();
        return favourite;
    }

}
