package com.sept.rest.webservices.restfulwebservices.classes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sept.rest.webservices.restfulwebservices.exceptions.DataNotFoundException;
import com.sept.rest.webservices.restfulwebservices.products.Product;

@Service
public class ClassService {
	
	@Autowired
	private ClassJpaRepository classRepository;

	
	public void create(Class class_) {
		classRepository.save(class_);
	}
	
	public List<Class> findById(Long id) {
		List<Class> class_ = this.classRepository.findClassesByUser(id);
		if (class_ == null) {
			throw new DataNotFoundException("Class with id " + id + " can't be found.");
		}
		return class_;
	}

}
