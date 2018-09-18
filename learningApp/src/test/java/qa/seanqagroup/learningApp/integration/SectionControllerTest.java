package qa.seanqagroup.learningApp.integration;

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
public class SectionControllerTest {

	private static ExtentHtmlReporter htmlReporter;
	private static ExtentReports extent = new ExtentReports();
	private ExtentTest test;

	@Autowired
	private MockMvc mvc;

	@BeforeClass
	public static void setUpBeforeClass() {
		htmlReporter = new ExtentHtmlReporter(
				"C:\\Users\\Admin\\Desktop\\ExtentReports\\SectionControllerTestReport.html");
		extent.attachReporter(htmlReporter);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		extent.flush();
	}

	@Test
	public void addSectionTest() throws Exception {
		test = extent.createTest("SectionController add section");
		try {
			mvc.perform(MockMvcRequestBuilders.post("/section/add").contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("sectionName", "Energy").param("sectionContent", "Think").param("moduleId", "3"))
					.andExpect(status().isOk());
			test.pass("Added section to database");
		} catch (AssertionError e) {
			test.fail("Didn't add module to database");
		}
	}

	@Test
	public void addYoutubeTest() throws Exception {
		test = extent.createTest("SectionController add youtube link");
		try {
			mvc.perform(
					MockMvcRequestBuilders.post("/section/youtube").contentType(MediaType.APPLICATION_FORM_URLENCODED)
							.param("videoUrl", "www.youtube.com").param("sectionid", "1")
							.param("videoName", "Chi teach you to control your inner chi").param("trainerId", "1"))
					.andExpect(status().isOk());
			test.pass("Added youtube URL to database");
		} catch (AssertionError e) {
			test.fail("Didn't add youtube URL to database");
		}
	}

	@Test
	public void getCoursesTest() throws Exception {
		test = extent.createTest("SectionController GET all sections");
		try {
			mvc.perform(MockMvcRequestBuilders.get("/section/searchSection")).andExpect(status().isOk());
			test.pass("GET all section details as JSON array");
		} catch (Exception e) {
			test.fail("Failed to GET all section details as JSON array");
		}
	}

}
