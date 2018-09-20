package qa.seanqagroup.learningApp.controller;

import com.google.gson.Gson;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import qa.seanqagroup.learningApp.model.Course;
import qa.seanqagroup.learningApp.model.Module;
import qa.seanqagroup.learningApp.repository.CourseRepository;
import qa.seanqagroup.learningApp.repository.ModuleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/searchCourse")
    public String getCourseIdName() {
        Gson gson = new Gson();
        return gson.toJson(courseRepo.findAll());
    }

    @GetMapping("/trainerCourses/{trainerid}")
    public String getCoursesByTrainer(@PathVariable(value = "trainerid") Long trainerId) {
        List<Course> trainerCourses = new ArrayList<>();
        List<Course> courses = courseRepo.findAll();
        for (Course eachCourse : courses) {
            if (eachCourse.getTrainerId() == trainerId) trainerCourses.add(eachCourse);
        }
        Gson gson = new Gson();
        return gson.toJson(trainerCourses);
    }

    @GetMapping("/{courseId}")
    public String getCourseDetails(@PathVariable(value = "courseId") Long courseId){
        Gson gson = new Gson();
        Optional<Course> course = courseRepo.findById(courseId);
        return  gson.toJson(course);
    }
}
