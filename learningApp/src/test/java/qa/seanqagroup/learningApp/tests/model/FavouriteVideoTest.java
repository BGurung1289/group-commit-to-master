package qa.seanqagroup.learningApp.tests.model;

import static org.junit.Assert.*;

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
import qa.seanqagroup.learningApp.compositekey.FavouriteVideoKey;
import qa.seanqagroup.learningApp.compositekey.SectionHasVideoKey;
import qa.seanqagroup.learningApp.model.FavouriteVideo;
import qa.seanqagroup.learningApp.model.SectionHasVideo;
import qa.seanqagroup.learningApp.repository.FavouriteVideoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { LearningAppApplication.class })
@DataJpaTest
public class FavouriteVideoTest {

	private static ExtentHtmlReporter htmlReporter;
	private static ExtentReports extent = new ExtentReports();
	private ExtentTest parentTest;
	private ExtentTest test;

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private FavouriteVideoRepository fvRepository;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		htmlReporter = new ExtentHtmlReporter("C:\\Users\\Admin\\Desktop\\ExtentReports\\FavouriteVideoTestReport.html");
		extent.attachReporter(htmlReporter);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		extent.flush();
	}

	@Test
	public void getSetTest() {
		FavouriteVideo favouriteVideo = new FavouriteVideo();
		favouriteVideo.setUserId(1);
		favouriteVideo.setVideoId(1);

		parentTest = extent.createTest("SET and GET Favourite Video Object");
		ExtentTest childTest = parentTest.createNode("GET User ID");
		ExtentTest childTest2 = parentTest.createNode("GET Video ID");

		try {
			assertEquals(1, favouriteVideo.getUserId());
			childTest.pass(MarkupHelper.createLabel("Found User ID: 1", ExtentColor.BLUE));
		} catch (AssertionError e) {
			childTest.fail(MarkupHelper.createLabel("Failed to find User ID: 1", ExtentColor.RED));
		}

		try {
			assertEquals(1, favouriteVideo.getVideoId());
			childTest2.pass(MarkupHelper.createLabel("Found Video ID: 1", ExtentColor.BLUE));
		} catch (AssertionError e) {
			childTest2.fail(MarkupHelper.createLabel("Failed get video ID: 1", ExtentColor.RED));
		}
	}

	@Test
	public void saveAndGetFromRepository() {
		FavouriteVideo favouriteVideo = new FavouriteVideo();
		favouriteVideo.setUserId(1);
		favouriteVideo.setVideoId(1);

		entityManager.persist(favouriteVideo);
		entityManager.flush();
		FavouriteVideoKey favouriteVideoKey = new FavouriteVideoKey(favouriteVideo.getUserId(),
				favouriteVideo.getVideoId());
		FavouriteVideo favouriteVideo2 = entityManager.find(FavouriteVideo.class, favouriteVideoKey);
		

		test = extent.createTest("Check FavouriteVideo object is saved to database");
		try {
			assertTrue(favouriteVideo2.getUserId() == 1);
			test.pass(MarkupHelper.createLabel("Found FavouriteVideo in database: userId: 1, videoId: 1", ExtentColor.GREEN));
		} catch (AssertionError e) {
			test.fail(MarkupHelper.createLabel("Failed to save to database: userId: 1, videoId: 1", ExtentColor.RED));
		}
	}


}
