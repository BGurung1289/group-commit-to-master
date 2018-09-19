package qa.seanqagroup.learningApp.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qa.seanqagroup.learningApp.model.ModuleExam;
import qa.seanqagroup.learningApp.model.UserHasExam;
import qa.seanqagroup.learningApp.repository.UserHasExamRepo;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserExamController {
	
	@Autowired
	UserHasExamRepo userExamRepo;
	
	@PostMapping("/usertest")
	public UserHasExam createTest(UserHasExam usertest){
		return userExamRepo.save(usertest);
	}
	
	@GetMapping("/getalltest/{id}")
	public String getExamsByUserId(@PathVariable(value="id") Long userId) throws JSONException {
		ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
		ArrayList<UserHasExam> usertests = userExamRepo.findAllByUserId(userId);
		JSONObject obj;
		for(UserHasExam test : usertests) {
			obj = new JSONObject();
			obj.put("testId", test.getTestId());
			arr.add(obj);
		}
		
		return arr.toString();
	}

}
