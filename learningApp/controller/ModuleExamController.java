package qa.seanqagroup.learningApp.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qa.seanqagroup.learningApp.model.Answer;
import qa.seanqagroup.learningApp.model.Course;
import qa.seanqagroup.learningApp.model.ModuleExam;
import qa.seanqagroup.learningApp.model.TestQuestionModel;
import qa.seanqagroup.learningApp.model.User;
import qa.seanqagroup.learningApp.model.UserTakesCourse;
import qa.seanqagroup.learningApp.repository.AnswerRepo;
import qa.seanqagroup.learningApp.repository.ModuleExamRepo;
import qa.seanqagroup.learningApp.repository.TestQuestionRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ModuleExamController {
	
	@Autowired
	ModuleExamRepo testRepo;
	
	@Autowired
	TestQuestionRepository questionRepo;
	
	@Autowired
	AnswerRepo answerRepo;
	
	
	@PostMapping("/newtest")
	public ModuleExam createTest(@Valid @RequestBody ModuleExam test){
		return testRepo.save(test);
	}
	
	@GetMapping("/module/{moduleId}/getModuleTest")
	public Long getTestByModule(@PathVariable(value = "moduleId") Long moduleId) {
		
		ModuleExam test = testRepo.findTestByModuleId(moduleId);
		
		return test.getTestId();
	}
	
	@GetMapping("/test/{testId}/getQuestions")
	public String getAllQuestionsByTestId(@PathVariable(value = "testId") Long testId) {
		
		ArrayList<Long> questionIds = new ArrayList();
		ArrayList<JSONObject> questions = new ArrayList();
		
		for (TestQuestionModel question : questionRepo.findAll()) {
			if(question.getTestId() == testId) 
				questionIds.add(question.getTestQuestionId());
		}
		
		for (Long questionId : questionIds) {
			JSONObject question = new JSONObject();
			TestQuestionModel q = questionRepo.findById(questionId).orElse(null);
			question.put("question", q.getQuestionContent());

			ArrayList<JSONObject> answers = new ArrayList();			
			for (Answer answer : answerRepo.findAll()) {
				JSONObject answerInfo = new JSONObject();
				if (answer.getTestQuestionId() == questionId) {
					answerInfo.put("answer", answer.getAnswerContent());
					answerInfo.put("id", answer.getAnswerId());
					answerInfo.put("correct", answer.isCorrect());
					answers.add(answerInfo);
				}
			}
			question.put("answers", answers);							
			questions.add(question);
		}
		
		return questions.toString();
	}
	
}
