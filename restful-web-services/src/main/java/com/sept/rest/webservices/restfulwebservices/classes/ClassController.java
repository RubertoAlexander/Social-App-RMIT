package com.sept.rest.webservices.restfulwebservices.classes;

import com.sept.rest.webservices.restfulwebservices.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ClassController {

    private final UserService userService;
    private final ClassService classService;

    @Autowired
    public ClassController(UserService userService, ClassService classService) {
        this.userService = userService;
        this.classService = classService;
    }

    @PostMapping(value = "/add/class")
    @ResponseBody
    public String createClass(@RequestBody ClassUser classUser) {
        try {
            classService.create(classUser);
            return "created";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(value = "find/classes/{id}")
    @ResponseBody
    public List<ClassUser> getClasses(@PathVariable long id) {

        List<ClassUser> classesById = classService.findById(id);

        if (classesById != null) {
            return classesById;
        }

        return null;

    }

}
