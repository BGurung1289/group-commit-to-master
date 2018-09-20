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
import org.springframework.web.bind.annotation.RestController;

import qa.seanqagroup.learningApp.model.Course;
import qa.seanqagroup.learningApp.model.Module;
import qa.seanqagroup.learningApp.model.Section;
import qa.seanqagroup.learningApp.repository.CourseRepository;
import qa.seanqagroup.learningApp.repository.ModuleRepository;
import qa.seanqagroup.learningApp.repository.SectionRepository;

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
}
