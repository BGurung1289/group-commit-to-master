package qa.seanqagroup.learningApp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import qa.seanqagroup.learningApp.model.Answer;
import qa.seanqagroup.learningApp.model.ModuleExam;
import qa.seanqagroup.learningApp.model.TestQuestionModel;
import qa.seanqagroup.learningApp.repository.AnswerRepo;
import qa.seanqagroup.learningApp.repository.ModuleExamRepo;
import qa.seanqagroup.learningApp.repository.TestQuestionRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ModuleExamController {

	@Autowired
	ModuleExamRepo testRepo;
	
	@Autowired
	TestQuestionRepository testQRepo;
	
	@Autowired
	AnswerRepo answerRepo;
	
	@PostMapping("/TestModel")
	public void createTest(@RequestBody String payload) { // When you submit the exam on my react page, the System.out line below will display the json array coming from React.
		try {
			System.out.println(payload);
			JsonParser parser = new JsonParser();
			JsonArray arr = parser.parse(payload).getAsJsonArray();
			ModuleExam exam = new ModuleExam();
			Long currentQuestion = (long) 0;
			 
			for (JsonElement json : arr) {
				TestQuestionModel questions = new TestQuestionModel();
				Answer answers = new Answer();
				JsonObject testNameObj = json.getAsJsonObject();
				String category = testNameObj.get("title").getAsString();
				String inputs = testNameObj.get("value").getAsString();
				
				if (category.equals("test_name")) {
					exam.setTestName(testNameObj.get("value").toString().replace("\"", ""));
					
				} else if (category.equals("totalMarks")) {
					exam.setTotalMarks((long) Integer.parseInt(testNameObj.get("value").toString().replace("\"", "")));
				}
				
				else if (category.equals("testDescription")) {
					exam.setTestDescription(testNameObj.get("value").toString().replace("\"", ""));
					exam.setModuleId((long) 3);
					testRepo.save(exam);
				}
				
				else if (category.indexOf("QC") != -1) {
					questions.setQuestionContent(testNameObj.get("value").toString().replaceAll("\"", ""));
					questions.setTestId(exam.getTestId());
					testQRepo.save(questions);
					currentQuestion = questions.getTestQuestionId();
				}
				
				else if (category.endsWith("a")) {
					answers.setAnswerContent(testNameObj.get("value").toString().replaceAll("\"", ""));
					answers.setCorrect(true);
					answers.setTestQuestionId(currentQuestion);
					answerRepo.save(answers);
				
				}
				else if (category.endsWith("b")) {
					if (inputs.equals("")) {     // if the answer does not have any input. 
			
					}
					else {
					// Because my JSON comes in a strange way, need to account for the speech marks that come with the answer identification.
					answers.setAnswerContent(testNameObj.get("value").toString().replaceAll("\"", ""));
					answers.setCorrect(false);
					answers.setTestQuestionId(currentQuestion);
					answerRepo.save(answers);
					}
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}

// What is the max value of total marks? 

// Need to work out how to determine which test it belongs to? this is being auto generated, so search for a way to tap into this.