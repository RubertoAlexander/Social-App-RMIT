package com.sept.rest.webservices.restfulwebservices.classes;

import com.sept.rest.webservices.restfulwebservices.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {

	private final ClassJpaRepository classRepository;

	@Autowired
	public ClassService(ClassJpaRepository classRepository) {
		this.classRepository = classRepository;
	}


	void create(ClassUser classUser) {
		classRepository.save(classUser);
	}
	
	public List<ClassUser> findById(Long id) {
		List<ClassUser> classesByUser = this.classRepository.findClassesByUser(id);
		if (classesByUser == null) {
			throw new DataNotFoundException("Class with id " + id + " can't be found.");
		}
		return classesByUser;
	}

}
