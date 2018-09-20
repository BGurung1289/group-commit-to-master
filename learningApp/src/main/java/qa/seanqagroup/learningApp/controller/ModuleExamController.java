package qa.seanqagroup.learningApp.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import qa.seanqagroup.learningApp.exceptions.ResourceNotFoundException;
import qa.seanqagroup.learningApp.model.Answer;
import qa.seanqagroup.learningApp.model.ModuleExam;
import qa.seanqagroup.learningApp.model.TestQuestionModel;
import qa.seanqagroup.learningApp.repository.AnswerRepo;
import qa.seanqagroup.learningApp.repository.ModuleExamRepo;
import qa.seanqagroup.learningApp.repository.TestQuestionRepository;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ModuleExamController {

	@Autowired
	ModuleExamRepo testRepo;

	@Autowired
	TestQuestionRepository questionRepo;

	@Autowired
	AnswerRepo answerRepo;
	
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

	@PostMapping("/newtest")
	public ModuleExam createTest(ModuleExam test) {
		return testRepo.save(test);
	}

	@PostMapping("/TestModel")
	public void createTest(@RequestBody String payload) {
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
					questionRepo.save(questions);
					currentQuestion = questions.getTestQuestionId();
				} else if (category.endsWith("a")) {
					answers.setAnswerContent(testNameObj.get("value").toString().replaceAll("\"", ""));
					answers.setCorrect(true);
					answers.setTestQuestionId(currentQuestion);
					answerRepo.save(answers);

				} else if (category.endsWith("b")) {
					if (inputs.equals("")) {
					} else {
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
	}

	@GetMapping("/gettest/{id}")
	public ModuleExam getExam(@PathVariable(value = "id") Long examId) {
		ModuleExam exam = testRepo.findById(examId)
				.orElseThrow(() -> new ResourceNotFoundException("Exam", "Id", examId));
		;
		return exam;
	}

}
