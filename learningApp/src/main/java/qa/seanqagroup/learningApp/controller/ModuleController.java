package qa.seanqagroup.learningApp.controller;

import java.util.List;

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
	public List<Section> getNumberOfSections(@PathVariable(value = "moduleId") Long moduleId) {
		// h'mta modulidt
		Module module = moduleRepo.getModuleByModuleId(moduleId);
		
		//h'mta samtliga sections som tillh;r modulen
		List<Section> sections = sectionRepo.getSectionsByModuleId(module.getModuleId());
		
		return sections;
	}

	@PostMapping("/add")
	public void createModule(Module module) {
		moduleRepo.save(module);
	}
}
