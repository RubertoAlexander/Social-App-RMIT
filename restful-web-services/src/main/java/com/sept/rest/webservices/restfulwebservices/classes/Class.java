package com.sept.rest.webservices.restfulwebservices.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sept.rest.webservices.restfulwebservices.lineitem.LineItem;
import com.sept.rest.webservices.restfulwebservices.user.User;

@Entity
@Table(name = "CLASSES")
public class Class {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long classId;
	
	@Column(name="USER_ID", nullable=false)
	private long user;

	@Column(name="class_name",unique=false,nullable = false)
	private String class_name;
	
	
	@Column(name="Description",unique=false,nullable = false)
	private String Description;
	
	@Column(name="Location",unique=false,nullable = false)
	private String Location;
	
	
	public long getUser() {
		return user;
	}

	public void setUser(long user) {
		this.user = user;
	}

	public void setClassName(String class1) {
		class_name = class1;
	}
	
	public String getClassName() {
		return class_name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	


}