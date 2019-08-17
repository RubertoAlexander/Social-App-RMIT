package com.sept.rest.webservices.restfulwebservices.products;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
	
	List<Product> findAll();
	Product findById(long id);
	
}
