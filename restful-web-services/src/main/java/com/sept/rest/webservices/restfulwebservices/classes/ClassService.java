package com.sept.rest.webservices.restfulwebservices.classes;

import com.sept.rest.webservices.restfulwebservices.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {
	
	@Autowired
	private ClassJpaRepository classRepository;

	
	public void create(ClassUser class_User_) {
		classRepository.save(class_User_);
	}
	
	public List<ClassUser> findById(Long id) {
		List<ClassUser> class_User_ = this.classRepository.findClassesByUser(id);
		if (class_User_ == null) {
			throw new DataNotFoundException("Class with id " + id + " can't be found.");
		}
		return class_User_;
	}

}
