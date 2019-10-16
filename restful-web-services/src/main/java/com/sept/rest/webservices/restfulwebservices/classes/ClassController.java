package com.sept.rest.webservices.restfulwebservices.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sept.rest.webservices.restfulwebservices.lineitem.LineItem;
import com.sept.rest.webservices.restfulwebservices.orders.OrderService;
import com.sept.rest.webservices.restfulwebservices.products.Product;
import com.sept.rest.webservices.restfulwebservices.products.ProductService;
import com.sept.rest.webservices.restfulwebservices.user.User;
import com.sept.rest.webservices.restfulwebservices.user.UserService;

@CrossOrigin(origins = "*")
@RestController
public class ClassController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ClassService classService;
	
	@PostMapping(value = "/add/class")
	@ResponseBody
	public String createClass(@RequestBody Class_ class_) {
		try {
			classService.create(class_);
			return "created";
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping(value = "find/classes/{id}")
	@ResponseBody
	public List<Class_> getClasses(@PathVariable long id) {
		
		List<Class_> class_ = classService.findById(id);
		class_.get(0).getClassName();
		if(class_ != null) {
			return class_;
		}
		
		return null;
		
	}

}
