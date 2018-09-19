
package qa.seanqagroup.learningApp.integration;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

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
import qa.seanqagroup.learningApp.LearningAppApplication;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { LearningAppApplication.class })
@AutoConfigureMockMvc
public class ModuleControllerTest {

	private static ExtentHtmlReporter htmlReporter;
	private static ExtentReports extent = new ExtentReports();
	private ExtentTest test;

	@Autowired
	private MockMvc mvc;

	@BeforeClass
	public static void setUpBeforeClass() {
		htmlReporter = new ExtentHtmlReporter(
				"C:\\Users\\Admin\\Desktop\\ModuleControllerTestReport.html");
		extent.attachReporter(htmlReporter);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		extent.flush();
	}
	
	
	@Test
	public void addModuleTest() throws Exception {
		test = extent.createTest("ModuleController add module");
		try {
			mvc.perform(MockMvcRequestBuilders.post("/module/add").contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("moduleName", "Control Chi(Energy)")
					.param("moduleDescription", "Think, breath, and control")
					.param("courseId", "15"))
					.andExpect(status().isOk());
			test.pass("Added module to database");
		} catch (AssertionError e) {
			test.fail("Didn't add module to database");
		}
	}
	
	
	

	@Test
	public void addExamTest() throws Exception {
		test = extent.createTest("ModuleExamController, adding new exam");
		try { 
			mvc.perform(MockMvcRequestBuilders.post("/TestModel")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("test_name", "testing")
					.param("total_marks", "20")
					.param("testDescription", "A long test")
					.param("QC1" , "why?" )
					.param("A1a", "yes")
					.param("A1b", "no")
					.param("A1b", "no1")
					.param("A1b" , "no2"))	
					.andExpect(status().isOk());
			test.pass("Add exam to the database; it is named 'testing', has a maximum score of 20 marks, has the following description 'A long test', has 1 question which asks 'why', 1 correct answer and 3 incorrect answers");
	
		} catch (AssertionError e) {
			test.fail("Failed to add exam to the database");
		}
	}

	public void getCoursesTest() throws Exception {
		test = extent.createTest("CourseController GET all courses");
		try {
			mvc.perform(MockMvcRequestBuilders.get("/course/searchCourse")).andExpect(status().isOk());
			test.pass("GET all course details as JSON array");
		} catch (Exception e) {
			test.fail("Failed to GET all course details as JSON array");
		}
	}


}

