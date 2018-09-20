package qa.seanqagroup.learningApp.controller;

import com.google.gson.Gson;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import qa.seanqagroup.learningApp.model.Module;
import qa.seanqagroup.learningApp.model.Section;
import qa.seanqagroup.learningApp.repository.ModuleRepository;
import qa.seanqagroup.learningApp.repository.SectionRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/module")
@CrossOrigin(origins = "http://localhost:3000")
public class ModuleController {

    @Autowired
    private ModuleRepository moduleRepo;

    @Autowired
    private SectionRepository sectionRepo;

    @GetMapping("/{moduleId}/getSections")
    public String getModuleSections(@PathVariable(value = "moduleId") Long moduleId) {
		Module module = moduleRepo.getModuleByModuleId(moduleId);		
		
		ArrayList<Section> sections = sectionRepo.getSectionsByModuleId(module.getModuleId());
		
		ArrayList<JSONObject> sectionInfo = new ArrayList();
		
		int sectionCounter = 0;
		
		for (Section section : sections) {
			JSONObject obj = new JSONObject();
			if (section.getModuleId() == moduleId) {
				obj.put("orderInModule", sectionCounter);
				obj.put("id", section.getSectionId());
				sectionInfo.add(obj);
				sectionCounter++;
			}
		}
		
		return sectionInfo.toString();
	}

    @PostMapping("/add")
    public void createModule(Module module) {
        moduleRepo.save(module);
    }

    @GetMapping("/searchModule")
    public String getCourseIdName() {
        Gson gson = new Gson();
        return gson.toJson(moduleRepo.findAll());
    }

    @GetMapping("/trainerModules/{courseId}")
    public String getCoursesByTrainer(@PathVariable(value = "courseId") Long courseId) {
        List<Module> trainerCourses = new ArrayList<>();
        List<Module> modules = moduleRepo.findAll();
        for (Module eachCourse : modules) {
            if (eachCourse.getCourseId() == courseId) trainerCourses.add(eachCourse);
        }
        Gson gson = new Gson();
        return gson.toJson(trainerCourses);
    }
}
