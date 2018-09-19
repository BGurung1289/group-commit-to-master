package qa.seanqagroup.learningApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import qa.seanqagroup.learningApp.model.Module;
import qa.seanqagroup.learningApp.repository.ModuleRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/module")
public class ModuleController {

	@Autowired
	private ModuleRepository moduleRepository;

	@PostMapping("/add")
	public void createModule(Module module) {
		moduleRepository.save(module);
	}
	
	@GetMapping("/searchModule")
	public String getCourseIdName() {
		Gson gson = new Gson();
		return gson.toJson(moduleRepository.findAll());
	}
}
