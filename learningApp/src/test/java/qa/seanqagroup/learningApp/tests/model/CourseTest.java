package qa.seanqagroup.learningApp.tests.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import qa.seanqagroup.learningApp.LearningAppApplication;
import qa.seanqagroup.learningApp.model.Course;
import qa.seanqagroup.learningApp.repository.CourseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { LearningAppApplication.class })
@DataJpaTest
public class CourseTest {

	private static ExtentHtmlReporter htmlReporter;
	private static ExtentReports extent = new ExtentReports();
	private ExtentTest parentTest;
	private ExtentTest test;

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CourseRepository courseRepository;

	@BeforeClass
	public static void setUpBeforeClass() {
		htmlReporter = new ExtentHtmlReporter("C:\\Users\\Admin\\Desktop\\ExtentReports\\CourseTestReport.html");
		extent.attachReporter(htmlReporter);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		extent.flush();
	}

	@Test
	public void getSetCourse() {
		Course course = new Course();
		course.setCourseName("Computer Science");
		course.setCourseDescription("Exciting world of CS");
		course.setTrainerId(1);

		parentTest = extent.createTest("SET and GET Course Object");
		ExtentTest childTest = parentTest.createNode("GET Course Name");
		ExtentTest childTest2 = parentTest.createNode("GET Description");
		ExtentTest childTest3 = parentTest.createNode("GET Trainer ID");

		try {
			assertEquals("Computer Science", course.getCourseName());
			childTest.pass(MarkupHelper.createLabel("Found Name: Computer Science", ExtentColor.BLUE));
		} catch (AssertionError e) {
			childTest.fail(MarkupHelper.createLabel("Failed to find name: Computer Science", ExtentColor.RED));
		}

		try {
			assertEquals("Exciting world of CS", course.getCourseDescription());
			childTest2.pass(MarkupHelper.createLabel("Found Description", ExtentColor.BLUE));
		} catch (AssertionError e) {
			childTest2.fail(MarkupHelper.createLabel("Failed get description", ExtentColor.RED));
		}

		try {
			assertEquals(1, course.getTrainerId());
			childTest3.pass(MarkupHelper.createLabel("Found Trainer ID: 1", ExtentColor.BLUE));
		} catch (AssertionError e) {
			childTest3.fail(MarkupHelper.createLabel("Failed to find trainer: 1", ExtentColor.RED));
		}

	}

	@Test
	public void saveAndGetFromRepository() {
		Course course = new Course();
		course.setCourseName("Computer Science");
		course.setCourseDescription("Exciting world of CS");
		course.setTrainerId(1);

		entityManager.persist(course);
		entityManager.flush();

		test = extent.createTest("Check Course object is saved to database");
		try {
			assertTrue(courseRepository.findById(course.getCourseId()).isPresent());
			test.pass(MarkupHelper.createLabel("Added course: courseName: Computer Science, description: Exciting world of CS, trainerId: 1", ExtentColor.GREEN));
		} catch (AssertionError e) {
			test.fail(MarkupHelper.createLabel("Failed Add: courseName: Computer Science, description: Exciting world of CS, trainerId: 1", ExtentColor.RED));
		}
	}

}
