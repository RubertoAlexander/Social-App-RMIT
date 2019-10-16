package com.sept.rest.webservices.restfulwebservices.classes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassJpaRepository extends JpaRepository<Class_, Long>{
	public List<Class_> findClassesByUser(long id);
}
