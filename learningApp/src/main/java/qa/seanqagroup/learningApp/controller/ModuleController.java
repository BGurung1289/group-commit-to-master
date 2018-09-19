package qa.seanqagroup.learningApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import qa.seanqagroup.learningApp.model.Module;
import qa.seanqagroup.learningApp.repository.ModuleRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ModuleController {

	@Autowired
	private ModuleRepository moduleRepository;

	@PostMapping("/module/add")
	public void createModule(Module module) {
		moduleRepository.save(module);
	}
}
