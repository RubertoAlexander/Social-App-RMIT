package com.sept.rest.webservices.restfulwebservices.location;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationJpaRepository extends JpaRepository<Location, Long> {
		public Location findLocationByUserId(Long user_id);
		public List<Location> findAll();
}
