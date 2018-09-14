package qa.seanqagroup.learningApp.tests.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import qa.seanqagroup.learningApp.LearningAppApplication;
import qa.seanqagroup.learningApp.model.Video;
import qa.seanqagroup.learningApp.repository.VideoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { LearningAppApplication.class })
@DataJpaTest
public class VideoTest {

	private static ExtentHtmlReporter htmlReporter;
	private static ExtentReports extent = new ExtentReports();
	private ExtentTest parentTest;
	private ExtentTest test;

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private VideoRepository videoRepository;

	@BeforeClass
	public static void setUpBeforeClass() {
		htmlReporter = new ExtentHtmlReporter("C:\\Users\\Admin\\Desktop\\ExtentReports\\VideoTestReport.html");
		extent.attachReporter(htmlReporter);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		extent.flush();
	}

	@Test
	public void getSetTest() {
		Video video = new Video();
		video.setVideoName("How to for loop");
		video.setVideoUrl("http://www.youtube.com");
		video.setYoutube(true);
		video.setTrainerId(1);

		parentTest = extent.createTest("SET and GET Video Object");
		ExtentTest childTest = parentTest.createNode("GET Video Name");
		ExtentTest childTest2 = parentTest.createNode("GET URL");
		ExtentTest childTest3 = parentTest.createNode("GET isYoutube");
		ExtentTest childTest4 = parentTest.createNode("GET Trainer id");

		try {
			assertEquals("How to for loop", video.getVideoName());
			childTest.pass(MarkupHelper.createLabel("Found Name", ExtentColor.BLUE));
		} catch (AssertionError e) {
			childTest.fail(MarkupHelper.createLabel("Failed to find name", ExtentColor.RED));
		}

		try {
			assertEquals("http://www.youtube.com", video.getVideoUrl());
			childTest2.pass(MarkupHelper.createLabel("Found URL", ExtentColor.BLUE));
		} catch (AssertionError e) {
			childTest2.fail(MarkupHelper.createLabel("Failed get URL", ExtentColor.RED));
		}

		try {
			assertEquals(true, video.isYoutube());
			childTest3.pass(MarkupHelper.createLabel("Found youtube boolean", ExtentColor.BLUE));
		} catch (AssertionError e) {
			childTest3.fail(MarkupHelper.createLabel("Failed to find boolean", ExtentColor.RED));
		}
		try {
			assertEquals(1, video.getTrainerId());
			childTest3.pass(MarkupHelper.createLabel("Found trainer ID", ExtentColor.BLUE));
		} catch (AssertionError e) {
			childTest3.fail(MarkupHelper.createLabel("Failed to find trainer", ExtentColor.RED));
		}

	}

	@Test
	public void saveAndGetFromRepository() {
		Video video = new Video();
		video.setVideoName("How to for loop");
		video.setVideoUrl("http://www.youtube.com");
		video.setYoutube(true);
		video.setTrainerId(1);

		entityManager.persist(video);
		entityManager.flush();

		test = extent.createTest("Check Module object is saved to database");
		try {
			assertTrue(videoRepository.findById(video.getVideoId()).isPresent());
			test.pass(MarkupHelper.createLabel("Found video in database", ExtentColor.GREEN));
		} catch (AssertionError e) {
			test.fail(MarkupHelper.createLabel("Failed to save to database", ExtentColor.RED));
		}
	}

}
