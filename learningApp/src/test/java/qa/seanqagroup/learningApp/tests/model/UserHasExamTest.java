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
			createUserTest.pass("User with id '1' has been assigned exam with id '1'");
		}catch(Exception e) {
			createUserTest.fail("failed to assign user to exam");
		}
		
		UserHasExamKey key = new UserHasExamKey(userExam.getUserId(), userExam.getTestId());
		UserHasExam temp = entityManager.find(UserHasExam.class, key);
		
		try{
			assertTrue(temp.getUserId().equals((long) 1));
			findUserTest.pass("user with id '1' and assigned exam found");
		}catch(AssertionError e) {
			findUserTest.fail("failed to find user with id '1' and assigned exam");
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
			setCompletedTest.pass("Assigned exam created and set to completed");
		}catch(Exception e) {
			setCompletedTest.fail("failed to set assigned exam to completed");
		}
		
		try{
			assertTrue(userExam.isCompleted() == true);
			getCompletedTest.pass("Assigned exam found as completed");
		}catch(AssertionError e) {
			getCompletedTest.fail("failed to find completion satus of exam");
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
			setMarksTest.pass("Assigned exam created and marks correct set as '10'");
		}catch(Exception e) {
			setMarksTest.fail("failed to set marks correct of assigned exam");
		}
		
		try{
			assertTrue(userExam.getMarksCorrect().equals((long) 10));
			getMarksTest.pass("Total marks of assigned exam found as '10'");
		}catch(AssertionError e) {
			getMarksTest.fail("failed to find marks correct of assigned exam");
		}
	}
}
