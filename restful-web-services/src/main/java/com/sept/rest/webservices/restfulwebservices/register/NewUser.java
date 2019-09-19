package com.sept.rest.webservices.restfulwebservices.register;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class NewUser {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="Id", nullable = false)
	private Long id;
	
	@Column(name="Username", length=64, nullable=false)
	private String username;
	
	@Column(name="Password", length=64, nullable=false)
	private String password;
	
	@Column(name="cashBalance", length=64, nullable=true)
	private double cashBalance;
	
	public double getCashBalance() {
		return cashBalance;
	}

	public void setCashBalance(double cashBalance) {
		this.cashBalance = cashBalance;
	}

	public NewUser() {
		
	}

	public NewUser(String username, String password) {
		this.username = username;
		this.password = password;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	//bcrypt
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewUser other = (NewUser) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
}