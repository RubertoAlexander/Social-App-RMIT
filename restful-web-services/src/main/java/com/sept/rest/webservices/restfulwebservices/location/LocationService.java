package com.sept.rest.webservices.restfulwebservices.location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sept.rest.webservices.restfulwebservices.products.ProductJpaRepository;

@Service
public class LocationService {

	@Autowired
	private LocationJpaRepository locationRepository;

	public void saveLocation(Location location) {
		Location locationExist =  locationRepository.findLocationByUserId(location.getUserId());
		try {
			if(locationExist.getLocation_id() != null) {
				locationRepository.deleteById(locationExist.getLocation_id());
				
			}
			
		} catch (Exception e) {
			locationRepository.save(location);
			
		}
		locationRepository.save(location);
		
		
		
	}
	
	public Location getLocationOfUser(Long user_id) {
		return locationRepository.findLocationByUserId(user_id);
	}
	
	public List<Location> getAllLocations() {
		
		return locationRepository.findAll();
	}
	
}
