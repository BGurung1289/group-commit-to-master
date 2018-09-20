package qa.seanqagroup.learningApp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qa.seanqagroup.learningApp.model.ModuleExam;
import qa.seanqagroup.learningApp.model.UserHasExam;
import qa.seanqagroup.learningApp.repository.UserHasExamRepo;

@RestController
public class UserExamController {
	
	@Autowired
	UserHasExamRepo userExamRepo;
	
	@PostMapping("/usertest")
	public UserHasExam createTest(@Valid UserHasExam usertest){
		
		return userExamRepo.save(usertest);
	}

}
