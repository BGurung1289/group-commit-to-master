package qa.seanqagroup.learningApp.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import qa.seanqagroup.learningApp.model.Course;
import qa.seanqagroup.learningApp.model.Module;
import qa.seanqagroup.learningApp.model.UserTakesCourse;
import qa.seanqagroup.learningApp.repository.CourseRepository;
import qa.seanqagroup.learningApp.repository.ModuleRepository;

@RestController
@RequestMapping("/course")
@CrossOrigin(origins = "http://localhost:3000")
public class CourseController {
	
	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private ModuleRepository moduleRepo;
		
	@GetMapping("/{courseId}/getModules")
	public String getCourseModules(@PathVariable(value = "courseId") Long courseId, Pageable pageable) {	//Page<Module>
		Course course = courseRepo.getCourseByCourseId(courseId);
			
		Page<Module> modules = moduleRepo.getModulesByCourseId(course.getCourseId(), pageable);
		
		ArrayList<JSONObject> moduleInfo = new ArrayList();
		
		for (Module module : modules) {
			JSONObject obj = new JSONObject();
			if (module.getCourseId() == courseId) {
				obj.put("id", module.getModuleId());
				obj.put("name", module.getModuleName());
				moduleInfo.add(obj);
			}
		}
		
		return moduleInfo.toString();		
	}
	
	@PostMapping("/add")
	public void createCourse(Course course, @RequestParam("madeByTrainerId") Long madeByTrainerId) {
		course.setTrainerId(madeByTrainerId);
		courseRepo.save(course);
	}


}