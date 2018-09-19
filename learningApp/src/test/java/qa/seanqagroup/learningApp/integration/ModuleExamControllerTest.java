package qa.seanqagroup.learningApp.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import qa.seanqagroup.learningApp.LearningAppApplication;
import qa.seanqagroup.learningApp.model.ModuleExam;
import qa.seanqagroup.learningApp.repository.ModuleExamRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { LearningAppApplication.class })
@AutoConfigureMockMvc
public class ModuleExamControllerTest {
	
	private static ExtentHtmlReporter htmlReporter;
	private static ExtentReports extent = new ExtentReports();
	private ExtentTest test;
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ModuleExamRepo testRepo;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		htmlReporter = new ExtentHtmlReporter(
				"C:\\Users\\Admin\\Desktop\\ExtentReports\\ModuleExamControllerTestReport.html");
		extent.attachReporter(htmlReporter);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		extent.flush();
	}
	
//	@Before
//	public void clearDB() {
//		testRepo.deleteAll();
//	}
	
	@Test
	public void findAndRetrieveTest() throws Exception{
		ModuleExam testExam = new ModuleExam();
		testExam.setTestName("test Exam");
		testExam.setTestDescription("example description");
		testExam.setTotalMarks((long) 10);
		testExam.setModuleId((long) 3);
		testRepo.save(testExam);
		mvc.perform(get("/gettest/"+ testExam.getTestId())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.testName", is("test Exam")));
	}
	@Test
	public void addExamToDatabase() throws Exception{
		test = extent.createTest("Exam Controller, Adding new exam");
		try{
			mvc.perform(MockMvcRequestBuilders.post("/newtest")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("testName", "sample exam")
				.param("testDescription", "sample exam for testing")
				.param("totalMarks", "10")
				.param("moduleId", "5"))
		        .andExpect(status().isOk());
			test.pass("Added exam to database with name of 'sample exam', description of 'sample exam for testing', total marks of '10' and module id of '5'");
		}catch (AssertionError e) {
			test.fail("failed to add exam to database");
		}
	}
	
	
	@Test
	public void addExamQuestionAnswer() throws Exception{
		test = extent.createTest("Exam Controller, Adding new exam with questions and answers");
		try{
			mvc.perform(MockMvcRequestBuilders.post("/TestModel")
				.contentType(MediaType.APPLICATION_JSON)
				.content("[{\"title\":\"test_name\",\"value\":\"test exam\"},{\"title\":\"totalMarks\",\"value\":\"10\"},{\"title\":\"testDescription\",\"value\":\"desc\"},{\"title\":\"QC1\",\"value\":\"test question\"}, {\"title\":\"A1a\",\"value\":\"correct\"},{\"title\":\"A1b\",\"value\":\"false\"}]"))
		        .andExpect(status().isOk());
			test.pass("Added exam to database with name of 'test', description of 'desc', total marks of '10' and module id of '5', with example questions and answers");
		}catch (AssertionError e) {
			test.fail("failed to add exam/questions/answers to database");
		}
	}

}
