package qa.seanqagroup.learningApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qa.seanqagroup.learningApp.repository.ModuleExamRepo;
import qa.seanqagroup.learningApp.repository.TestQuestionRepository;




@CrossOrigin(origins = "http://localhost:3000") // whats this about??
@RestController
@RequestMapping("/api")
public class TestQuestionController {

@Autowired
TestQuestionRepository tQRepo;

@Autowired
ModuleExamRepo mER;
		
}


