package com.sept.rest.webservices.restfulwebservices.orders;

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
import com.sept.rest.webservices.restfulwebservices.register.NewUser;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "local_date")
	private LocalDate date;

	private boolean paid = false;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private NewUser user;

	@OneToMany(cascade = CascadeType.ALL)
	@Transient
	List<LineItem> lineItems;

	protected Order() {
		super();
		lineItems = new ArrayList<>();
	}

	@Transient
	public double getTotalPrice() {
		double total = 0;
		for (LineItem item : lineItems) {
			total += item.getProduct().getPrice();
		}
		return total;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public NewUser getUser() {
		return user;
	}

	public void setUser(NewUser user) {
		this.user = user;
	}

	public List<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lineItems == null) ? 0 : lineItems.hashCode());
		result = prime * result + (paid ? 1231 : 1237);
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Order other = (Order) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lineItems == null) {
			if (other.lineItems != null)
				return false;
		} else if (!lineItems.equals(other.lineItems))
			return false;
		if (paid != other.paid)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}