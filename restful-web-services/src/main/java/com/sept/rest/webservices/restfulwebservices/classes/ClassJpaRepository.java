package com.sept.rest.webservices.restfulwebservices.classes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassJpaRepository extends JpaRepository<ClassUser, Long>{
	public List<ClassUser> findClassesByUser(long id);
}
