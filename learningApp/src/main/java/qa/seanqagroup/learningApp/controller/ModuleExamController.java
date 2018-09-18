package qa.seanqagroup.learningApp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qa.seanqagroup.learningApp.model.ModuleExam;
import qa.seanqagroup.learningApp.repository.ModuleExamRepo;

@RestController
public class ModuleExamController {
	
	@Autowired
	ModuleExamRepo testRepo;
	
	@PostMapping("/newtest")
	public ModuleExam createTest(@Valid @RequestBody ModuleExam test){
		return testRepo.save(test);
	}

}
