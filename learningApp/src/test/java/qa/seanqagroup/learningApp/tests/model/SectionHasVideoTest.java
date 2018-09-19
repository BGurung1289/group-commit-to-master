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
import qa.seanqagroup.learningApp.compositekey.SectionHasVideoKey;
import qa.seanqagroup.learningApp.model.SectionHasVideo;
import qa.seanqagroup.learningApp.repository.SectionHasVideoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { LearningAppApplication.class })
@DataJpaTest
public class SectionHasVideoTest {

	private static ExtentHtmlReporter htmlReporter;
	private static ExtentReports extent = new ExtentReports();
	private ExtentTest parentTest;
	private ExtentTest test;

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private SectionHasVideoRepository shvRepository;

	@BeforeClass
	public static void setUpBeforeClass() {
		htmlReporter = new ExtentHtmlReporter("C:\\Users\\Admin\\Desktop\\ExtentReports\\SectionHasVideoTestReport.html");
		extent.attachReporter(htmlReporter);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		extent.flush();
	}

	@Test
	public void getSetTest() {
		SectionHasVideo sectionHasVideo = new SectionHasVideo();
		sectionHasVideo.setSectionId(1);
		sectionHasVideo.setVideoId(1);

		parentTest = extent.createTest("SET and GET SectionHasVideo Object");
		ExtentTest childTest = parentTest.createNode("GET Section ID");
		ExtentTest childTest2 = parentTest.createNode("GET Video ID");

		try {
			assertEquals(1, sectionHasVideo.getSectionId());
			childTest.pass(MarkupHelper.createLabel("Found Section ID: 1", ExtentColor.BLUE));
		} catch (AssertionError e) {
			childTest.fail(MarkupHelper.createLabel("Failed to find section ID: 1", ExtentColor.RED));
		}

		try {
			assertEquals(1, sectionHasVideo.getVideoId());
			childTest2.pass(MarkupHelper.createLabel("Found Video ID: 1", ExtentColor.BLUE));
		} catch (AssertionError e) {
			childTest2.fail(MarkupHelper.createLabel("Failed get video ID: 1", ExtentColor.RED));
		}

	}

	@Test
	public void saveAndGetFromRepository() {
		SectionHasVideo sectionHasVideo = new SectionHasVideo();
		sectionHasVideo.setSectionId(1);
		sectionHasVideo.setVideoId(1);

		entityManager.persist(sectionHasVideo);
		entityManager.flush();
		SectionHasVideoKey sectionHasVideoKey = new SectionHasVideoKey(sectionHasVideo.getSectionId(),
				sectionHasVideo.getVideoId());
		SectionHasVideo sectionHasVideo2 = entityManager.find(SectionHasVideo.class, sectionHasVideoKey);

		test = extent.createTest("Check SectionHasVideo object is saved to database");
		try {
			assertTrue(sectionHasVideo2.getSectionId() == 1);
			test.pass(MarkupHelper.createLabel("Found SectionHasVideo: sectionId: 1, videoId: 1", ExtentColor.GREEN));
		} catch (AssertionError e) {
			test.fail(MarkupHelper.createLabel("Failed to save: sectionId: 1, videoId: 1", ExtentColor.RED));
		}
	}

}
