package qa.seanqagroup.learningApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import qa.seanqagroup.learningApp.model.Course;
import qa.seanqagroup.learningApp.repository.CourseRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseRepository courseRepository;

	@PostMapping("/add")
	public void createCourse(Course course, @RequestParam("madeByTrainerId") Long madeByTrainerId) {
		course.setTrainerId(madeByTrainerId);
		courseRepository.save(course);
	}
	
}
