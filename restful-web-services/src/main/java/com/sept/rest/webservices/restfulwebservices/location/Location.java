package com.sept.rest.webservices.restfulwebservices.location;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="LOCATION")
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long location_id;

    @NotBlank
    private String location;

    @NotNull
    private Long userId;
	
	// unix time
    @NotNull
    private Long date;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long user_id) {
		this.userId = user_id;
	}
	
	public Location() {
		
	}
	
	public Location(Long location_id, String location, Long user_id, Long date) {
	
		this.location_id = location_id;
		this.location = location;
		this.userId = user_id;
		this.date = date;
	}

    @Override
    public String toString() {
        return "Location [location_id=" + location_id + ", location=" + location + ", userId=" + userId + ", date="
                + date + "]";
    }

	public Long getLocation_id() {
		return location_id;
	}

	public void setLocation_id(Long location_id) {
		this.location_id = location_id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

}