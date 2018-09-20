package qa.seanqagroup.learningApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import qa.seanqagroup.learningApp.model.Course;
import qa.seanqagroup.learningApp.model.UserTakesCourse;
import qa.seanqagroup.learningApp.repository.CourseRepository;
import qa.seanqagroup.learningApp.repository.UserCourseRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserCourseController {
	
	private List<Course> courses;

	@Autowired
	private UserCourseRepository userCourseRepo;
	
	@Autowired
	private CourseRepository courseRepo;
		
	@GetMapping("/{userId}/getCourses")
	public List<UserTakesCourse> getAllCoursesByUserId(@PathVariable(value = "userId") Long userId) {		
		//List<Course> courses;
		
		List<UserTakesCourse> courseIds = userCourseRepo.findByUserId(userId); //get the ids of all courses the user takes 
		
		for(int i = 0; i < courseIds.size(); i++) {			//gets course objects based of the collected course id's 
			Optional<Course> course = courseRepo.findById(courseIds.get(i).getCourseId());
			
			//System.out.println(courseIds.get(i).getCourseId());
			//Course test = courseRepo.findById(courseIds.get(i).getCourseId()).orElse(null);
			if (course.isPresent()) {}
				courses.add(course.get());			
			/*else
				courses.add(new Course());*/
			//courses.add(courseRepo.findById(courseIds.get(i).getCourseId())).orElse(null);
		}
		
		return courseIds; 		
	}
	
	
	
}
