package com.sept.rest.webservices.restfulwebservices.products;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Long> {
	Product findByProductname(String productname);
	List<Product> findAll();
//	Product findById(long id);
	
}
