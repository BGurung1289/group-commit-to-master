package qa.seanqagroup.learningApp.integration;

import static org.junit.Assert.*;
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
import qa.seanqagroup.learningApp.repository.ModuleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { LearningAppApplication.class })
@AutoConfigureMockMvc
public class ModuleControllerTest {

	private static ExtentHtmlReporter htmlReporter;
	private static ExtentReports extent = new ExtentReports();
	private ExtentTest test;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ModuleRepository moduleRepository;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		htmlReporter = new ExtentHtmlReporter(
				"C:\\Users\\Admin\\Desktop\\ExtentReports\\ModuleControllerTestReport.html");
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

}
