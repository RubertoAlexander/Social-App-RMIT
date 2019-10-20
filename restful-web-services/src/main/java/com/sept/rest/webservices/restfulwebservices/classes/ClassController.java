package com.sept.rest.webservices.restfulwebservices.classes;

import com.sept.rest.webservices.restfulwebservices.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ClassController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ClassService classService;
	
	@PostMapping(value = "/add/class")
	@ResponseBody
	public String createClass(@RequestBody ClassUser class_User_) {
		try {
			classService.create(class_User_);
			return "created";
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping(value = "find/classes/{id}")
	@ResponseBody
	public List<ClassUser> getClasses(@PathVariable long id) {
		
		List<ClassUser> class_User_ = classService.findById(id);
		class_User_.get(0).getClassName();
		if(class_User_ != null) {
			return class_User_;
		}
		
		return null;
		
	}

}
