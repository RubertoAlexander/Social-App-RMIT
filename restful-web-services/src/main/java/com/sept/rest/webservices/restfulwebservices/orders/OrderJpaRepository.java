package com.sept.rest.webservices.restfulwebservices.orders;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long>{
	public List<Order> findAll();
}
