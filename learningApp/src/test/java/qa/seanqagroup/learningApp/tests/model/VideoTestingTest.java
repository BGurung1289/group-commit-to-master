package qa.seanqagroup.learningApp.tests.model;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import qa.seanqagroup.learningApp.LearningAppApplication;
import qa.seanqagroup.learningApp.model.SectionHasVideo;
import qa.seanqagroup.learningApp.repository.SectionHasVideoRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = { LearningAppApplication.class })
@AutoConfigureMockMvc
public class VideoTestingTest {

	private static ExtentHtmlReporter htmlReporter;
	private static ExtentReports extent = new ExtentReports();
	private ExtentTest test;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private SectionHasVideoRepository shvRepo;

	@BeforeClass
	public static void setUpBeforeClass() {
		htmlReporter = new ExtentHtmlReporter("C:\\Users\\Admin\\Desktop\\videoControllerTest.html");
		extent.attachReporter(htmlReporter);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		extent.flush();
	}

	@Test
	public void getVideoTest() throws Exception {
		test = extent.createTest("getting video GET");
		SectionHasVideo sectionHasVideo = new SectionHasVideo();
		sectionHasVideo.setSectionId(12);
		sectionHasVideo.setVideoId(8);
		shvRepo.save(sectionHasVideo);
		
		try {
			mvc.perform(get("/getVideo/12"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.ALL_VALUE))
			.andExpect(jsonPath("$[0].name", is("Chi Teach")));
			test.pass(MarkupHelper.createLabel("Test passed with details returned name:chi teach", ExtentColor.GREEN));
		}catch(AssertionError e) {
			test.fail(MarkupHelper.createLabel("Test failed with not expected results", ExtentColor.RED));
		}
	}

}
