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
import qa.seanqagroup.learningApp.repository.CourseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { LearningAppApplication.class })
@AutoConfigureMockMvc
public class CourseControllerTest {

	private static ExtentHtmlReporter htmlReporter;
	private static ExtentReports extent = new ExtentReports();
	private ExtentTest test;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private CourseRepository courseRepository;

	@BeforeClass
	public static void setUpBeforeClass() {
		htmlReporter = new ExtentHtmlReporter(
				"C:\\Users\\Admin\\Desktop\\ExtentReports\\CourseControllerTestReport.html");
		extent.attachReporter(htmlReporter);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		extent.flush();
	}

	@Test
	public void addCourseTest() throws Exception {
		test = extent.createTest("CourseController add course POST");
		try {
			mvc.perform(MockMvcRequestBuilders.post("/course/add").contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("courseName", "Learn to control your inner Chi")
					.param("courseDescription", "We teach you to control chi").param("madeByTrainerId", "1"))
					.andExpect(status().isOk());
			test.pass("Added courseName: Learn to control your inner Chi, courseDescription:"
					+ "We teach you to control chi, madeByTrainerId: 1");
		} catch (AssertionError e) {
			test.fail("Failed to add: courseName: Learn to control your inner Chi, courseDescription:" + 
					"We teach you to control chi, madeByTrainerId: 1");
		}
	}
}
