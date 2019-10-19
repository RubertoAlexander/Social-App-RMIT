package com.sept.rest.webservices.restfulwebservices.favourites;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="FAVOURITES")
public class Favourite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
  
	@NotBlank
	@Column(name = "Favourite")
	private String favourite;
	
	private String description;

	private String location;
	
	private Long userId;

	public Favourite(){

	}
	
	public Favourite(@NotBlank String favourite, String description, String location, Long userId) {
		super();
		this.favourite = favourite;
		this.description = description;
		this.location = location;
		this.userId = userId;
	}


	public String getFavourite() {
		return favourite;
	}

	public void setFavourite(String favourite) {
		this.favourite = favourite;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


	
	




	

}