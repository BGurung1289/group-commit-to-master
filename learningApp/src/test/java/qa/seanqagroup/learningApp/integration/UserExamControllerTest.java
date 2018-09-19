package qa.seanqagroup.learningApp.integration;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.AfterClass;
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
import qa.seanqagroup.learningApp.repository.ModuleExamRepo;
import qa.seanqagroup.learningApp.repository.UserHasExamRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { LearningAppApplication.class })
@AutoConfigureMockMvc
public class UserExamControllerTest {
	
	private static ExtentHtmlReporter htmlReporter;
	private static ExtentReports extent = new ExtentReports();
	private ExtentTest test;
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private UserHasExamRepo userExamRepo;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		htmlReporter = new ExtentHtmlReporter(
				"C:\\Users\\Admin\\Desktop\\ExtentReports\\UserExamControllerTestReport.html");
		extent.attachReporter(htmlReporter);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		extent.flush();
	}
	
	@Test
	public void addUserExamToDatabase() throws Exception{
		test = extent.createTest("UserHasExam Controller, User taking exam");
		try{
			mvc.perform(MockMvcRequestBuilders.post("/usertest")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("testId", "100")
				.param("userId", "1")
				.param("isCompleted", "false"))
		        .andExpect(status().isOk());
			test.pass("Adding user taking exam to database with test id of '1', user id of '1', total marks of '10' and completd status of 'false'");
		}catch(AssertionError e) {
			test.fail("failed to add user taking exam to database");
			
		}
	}

}
