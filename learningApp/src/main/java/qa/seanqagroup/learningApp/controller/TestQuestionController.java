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

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.util.JSON;

import qa.seanqagroup.learningApp.model.ModuleExam;
import qa.seanqagroup.learningApp.model.TestQuestionModel;
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


