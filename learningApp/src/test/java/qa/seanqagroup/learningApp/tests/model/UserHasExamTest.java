package qa.seanqagroup.learningApp.tests.model;

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
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import qa.seanqagroup.learningApp.LearningAppApplication;
import qa.seanqagroup.learningApp.compositekey.UserHasExamKey;
import qa.seanqagroup.learningApp.model.UserHasExam;
import qa.seanqagroup.learningApp.repository.UserHasExamRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {LearningAppApplication.class})

@DataJpaTest
public class UserHasExamTest {
	
	private static ExtentHtmlReporter htmlReporter;

	private static ExtentReports extent = new ExtentReports();

	private ExtentTest parentTest;

	private ExtentTest test;

	@BeforeClass
	public static void setUpBeforeClass() {
		htmlReporter = new ExtentHtmlReporter("C:\\Users\\Admin\\Desktop\\ExtentReports\\UserHasExamTestReport.html");
		extent.attachReporter(htmlReporter);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		extent.flush();
	}
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserHasExamRepo userHasExamRepo;
	
	@Test
	public void getUserExamByIdTest() {
		
		parentTest = extent.createTest("Creating and finding UserHasExam object");
		ExtentTest createUserTest = parentTest.createNode("Creating UserHasExam object");
		ExtentTest findUserTest = parentTest.createNode("Finding UserHasExam object");
		
		UserHasExam userExam = new UserHasExam();
		userExam.setUserId((long) 1);
		userExam.setTestId((long) 1);
		userExam.setCompleted(false);
		
		try{
			entityManager.persist(userExam);
			entityManager.flush();
			createUserTest.pass("User has been assigned exam");
		}catch(Exception e) {
			createUserTest.fail("User has not been assigned exam");
		}
		
		UserHasExamKey key = new UserHasExamKey(userExam.getUserId(), userExam.getTestId());
		UserHasExam temp = entityManager.find(UserHasExam.class, key);
		
		try{
			assertTrue(temp.getUserId().equals((long) 1));
			findUserTest.pass("user and assigned exam found");
		}catch(AssertionError e) {
			findUserTest.fail("user and assigned exam not found");
		}
	}
	
	@Test
	public void completedGetterTest() {
		
		parentTest = extent.createTest("Setting and getting exam completion");
		ExtentTest setCompletedTest = parentTest.createNode("Set exam completion");
		ExtentTest getCompletedTest = parentTest.createNode("Get exam completion");
		
		UserHasExam userExam = new UserHasExam();
		
		try{
			userExam.setCompleted(true);
			setCompletedTest.pass("Exam completion set");
		}catch(Exception e) {
			setCompletedTest.fail("Exam completion not set");
		}
		
		try{
			assertTrue(userExam.isCompleted() == true);
			getCompletedTest.pass("Exam completion state found");
		}catch(AssertionError e) {
			getCompletedTest.fail("Exam completion state not found");
		}
	}
	
	@Test
	public void marksCorrectGetterTest() {
		
		parentTest = extent.createTest("Setting and getting marks correct");
		ExtentTest setMarksTest = parentTest.createNode("Set marks correct");
		ExtentTest getMarksTest = parentTest.createNode("Get marks correct");
		
		UserHasExam userExam = new UserHasExam();
		
		try{
			userExam.setMarksCorrect((long) 10);
			setMarksTest.pass("Marks correct set");
		}catch(Exception e) {
			setMarksTest.fail("Marks correct not set");
		}
		
		try{
			assertTrue(userExam.getMarksCorrect().equals((long) 10));
			getMarksTest.pass("Marks correct found");
		}catch(AssertionError e) {
			getMarksTest.fail("Marks correct not found");
		}
	}
}
