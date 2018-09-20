package qa.seanqagroup.learningApp.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import qa.seanqagroup.learningApp.repository.ModuleExamRepo;
import qa.seanqagroup.learningApp.repository.TestQuestionRepository;




@CrossOrigin(origins = "http://localhost:3000")
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

}


