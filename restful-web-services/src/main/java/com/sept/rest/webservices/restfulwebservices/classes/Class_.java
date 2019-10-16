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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sept.rest.webservices.restfulwebservices.lineitem.LineItem;
import com.sept.rest.webservices.restfulwebservices.user.User;

@Entity
@Table(name = "CLASSES")
public class Class_ {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long classId;
	
	@NotNull
	@Column(name="USER_ID", nullable=false)
	private long user;

	@NotBlank
	@Column(name="class_name",unique=false,nullable = false)
	private String class_name;
	
	@NotBlank
	@Column(name="Description",unique=false,nullable = false)
	private String Description;
	
	@NotBlank
	@Column(name="Location",unique=false,nullable = false)
	private String Location;
	
	public Class_() {
		
	}
	
	public Class_(long user, String class_name, String description, String location) {
		this.user = user;
		this.class_name = class_name;
		Description = description;
		Location = location;
	}

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