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

import qa.seanqagroup.learningApp.model.ModuleExam;
import qa.seanqagroup.learningApp.model.TestQuestionModel;
import qa.seanqagroup.learningApp.repository.ModuleExamRepo;
import qa.seanqagroup.learningApp.repository.TestQuestionRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ModuleExamController {

	@Autowired
	ModuleExamRepo testRepo;
	
	@Autowired
	TestQuestionRepository testQRepo;
	
	@PostMapping("/TestModel")
	public void createTest(@RequestBody String payload) {
		try {
			JsonParser parser = new JsonParser();
			JsonArray arr = parser.parse(payload).getAsJsonArray();
			ModuleExam exam = new ModuleExam();
			TestQuestionModel questions = new TestQuestionModel();
			
			for (JsonElement json : arr) {
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
				}
				else if (category.indexOf("QC") != -1) {
					questions.setQuestionContent(testNameObj.get("value").toString().replaceAll("\"", ""));
				}
				else if (category.endsWith("a")) {
					
				}
			}
			
			exam.setModuleId((long) 3);
			testRepo.save(exam);
			
			questions.setTestId((long) 19);
			testQRepo.save(questions);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

// What is the max value of total marks? 

// Need to work out how to determine which test it belongs to? this is being auto generated, so search for a way to tap into this.