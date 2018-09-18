package qa.seanqagroup.learningApp.controller;

import java.io.Console;

import javax.validation.Valid;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.util.JSON;

import qa.seanqagroup.learningApp.repository.TestQuestionRepository;




@CrossOrigin(origins = "http://localhost:3000") // whats this about??
@RestController
@RequestMapping("/api")
public class TestQuestionController {

@Autowired
TestQuestionRepository tQRepo;

@PostMapping("/TestQuestionModel")
public void createTestQuestion(@RequestBody String payload) {
	try {
		payload = "{" + payload + "}";
		
		JSONObject jsonObj = new JSONObject(payload);
//		String title = jsonObj.getJSONObject("title")
		System.out.print(jsonObj);
//		JSONObject jsonObj = new JSONObject(payload);
//		System.out.println(jsonObj); 
	} catch (Exception e) {
		e.printStackTrace();
	}
	
//	return tQRepo.save(tQM);
}

//@PutMapping("/testQuestion/{id}")
//public TestQuestionModel updateTestQuestion(@PathVariable(value = "id") Long testId,
//		@Valid @RequestBody TestQuestionModel tQuestion) {
//	
//	TestQuestionModel tQM = tQRepo.findById(testQuestionId).orElseThrow(() -> new ResourceNotFoundException()
//}
//
//String[] eachobject = edit.split("},{");
//for(String value : eachobject) {
//System.out.println(value);

}