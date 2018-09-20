package qa.seanqagroup.learningApp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qa.seanqagroup.learningApp.model.Course;
import qa.seanqagroup.learningApp.model.SectionHasVideo;
import qa.seanqagroup.learningApp.model.UserTakesCourse;
import qa.seanqagroup.learningApp.model.Video;
import qa.seanqagroup.learningApp.repository.CourseRepository;
import qa.seanqagroup.learningApp.repository.UserCourseRepository;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserCourseController {
	
	@Autowired
	private UserCourseRepository userCourseRepo;
	
	@Autowired
	private CourseRepository courseRepo;
		
	@GetMapping("/{userId}/getCourses")
	public String getAllCoursesByUserId(@PathVariable(value = "userId") Long userId) {		
		
		ArrayList<Long> courseIds = new ArrayList();	
		ArrayList<JSONObject> courseInfo = new ArrayList();
		
		for (UserTakesCourse course : userCourseRepo.findAll()) {
			if (course.getUserId().equals(userId))
				courseIds.add(course.getCourseId());
		}
		
		for (Long courseId : courseIds) {
			JSONObject obj = new JSONObject(); 
			Course courseObj = courseRepo.getCourseByCourseId(courseId);
			if (courseObj != null) {
				obj.put("id", courseObj.getCourseId());
				obj.put("name", courseObj.getCourseName());
				courseInfo.add(obj);
			}
				
		}	
		return courseInfo.toString(); 		
	}
	
}
