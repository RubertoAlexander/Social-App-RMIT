package com.sept.rest.webservices.restfulwebservices.favourites;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sept.rest.webservices.restfulwebservices.location.Location;
import com.sept.rest.webservices.restfulwebservices.location.LocationJpaRepository;

@Service
public class FavouriteService {
	
	@Autowired
	private FavouriteJpaRepository favouriteRepository;
	
	
	// save favourite to database
	public void saveFavourite(Favourite favourite) {
		favouriteRepository.save(favourite);
		
	}
	
	public List<Favourite> findByUser(Long user_id) {
		return favouriteRepository.findFavouriteByUserId(user_id);
	}
	
	public List<Favourite> findAllFavs(){
		return favouriteRepository.findAll();
	}
	

}
