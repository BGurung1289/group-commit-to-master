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
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import qa.seanqagroup.learningApp.LearningAppApplication;
import qa.seanqagroup.learningApp.model.ModuleExam;
import qa.seanqagroup.learningApp.repository.ModuleExamRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { LearningAppApplication.class })

@DataJpaTest
public class ModuleExamTest {

	private static ExtentHtmlReporter htmlReporter;

	private static ExtentReports extent = new ExtentReports();

	private ExtentTest parentTest;

	private ExtentTest test;

	@BeforeClass
	public static void setUpBeforeClass() {
		htmlReporter = new ExtentHtmlReporter("C:\\Users\\Admin\\Desktop\\ExtentReports\\ModuleExamTestReport.html");
		extent.attachReporter(htmlReporter);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		extent.flush();
	}

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ModuleExamRepo moduleExamRepo;

	@Test
	public void getByIdTest() { 

		parentTest = extent.createTest("Creating and finding ModuleExam object");
		ExtentTest createTest = parentTest.createNode("Creating ModuleExam object");
		ExtentTest findTest = parentTest.createNode("Finding ModuleExam object");

		ModuleExam exam = new ModuleExam();
		exam.setTestName("test exam");
		exam.setTestDescription("desc");
		exam.setTotalMarks((long) 10);
		exam.setModuleId((long) 3);
		try {
			entityManager.persist(exam);
			entityManager.flush();
			createTest.pass("created exam with name of 'test exam', description of 'desc', total marks of '10', and module id of '3'");
		} catch (Exception e) {
			findTest.fail("exam not created");
		}
		try {
			assertTrue(moduleExamRepo.findById(exam.getTestId()).isPresent());
			findTest.pass("found created exam from database");
		} catch (AssertionError e) {
			findTest.fail("exam not found in database");
		}
	}

	@Test
	public void nameGetterTest() {
		
		parentTest = extent.createTest("Setting and getting ModuleExam name");
		ExtentTest setNameTest = parentTest.createNode("Set ModuleExam name");
		ExtentTest getNameTest = parentTest.createNode("Get ModuleExam name");

		ModuleExam exam = new ModuleExam();
		
		try{
			exam.setTestName("test exam");
			setNameTest.pass("exam created and name set as 'test exam'");
		}catch(Exception e) {
			setNameTest.fail("failed to set name of created exam");
		}
		
		try{
			assertTrue(exam.getTestName().equals("test exam"));
			getNameTest.pass("exam name found as 'test exam'");
		}catch(AssertionError e) {
			getNameTest.fail("failed to find exam name");
		}
	}

	@Test
	public void descGetterTest() {
		
		parentTest = extent.createTest("Setting and getting ModuleExam description");
		ExtentTest setDescTest = parentTest.createNode("Set ModuleExam description");
		ExtentTest getDescTest = parentTest.createNode("Get ModuleExam description");

		ModuleExam exam = new ModuleExam();
		
		try{
			exam.setTestDescription("desc");
			setDescTest.pass("exam created and description set as 'desc'");
		}catch(Exception e) {
			setDescTest.fail("failed to set description of created exam");
		}
		
		try{
			assertTrue(exam.getTestDescription().equals("desc"));
			getDescTest.pass("exam description found as 'desc'");
		}catch(AssertionError e) {
			getDescTest.fail("failed to find description of exam");
		}
	}

	@Test
	public void marksGetterTest() {
		
		parentTest = extent.createTest("Setting and getting ModuleExam total marks");
		ExtentTest setMarkTest = parentTest.createNode("Set ModuleExam total marks");
		ExtentTest getMarkTest = parentTest.createNode("Get ModuleExam total marks");

		ModuleExam exam = new ModuleExam();
		
		try{
			exam.setTotalMarks((long) 10);
			setMarkTest.pass("created exam and set total marks as '10'");
		}catch(Exception e) {
			setMarkTest.fail("failed to set total marks of created exam");
		}
		
		try{
			assertTrue(exam.getTotalMarks().equals((long) 10));
			getMarkTest.pass("total marks of exam found as '10'");
		}catch(AssertionError e) {
			getMarkTest.fail("failed to find total marks of exam");
		}
	}

	@Test
	public void moduleGetterTest() {
		
		parentTest = extent.createTest("Setting and getting ModuleExam module id");
		ExtentTest setModuleIdTest = parentTest.createNode("Set ModuleExam module id");
		ExtentTest getModuleIdTest = parentTest.createNode("Get ModuleExam module id");

		ModuleExam exam = new ModuleExam();
		
		try{
			exam.setModuleId((long) 3);
			setModuleIdTest.pass("created exam and set module id as '3'");
		}catch(Exception e) {
			setModuleIdTest.fail("failed to set module id of created exam");
		}
		
		try{
			assertTrue(exam.getModuleId().equals((long) 3));
			getModuleIdTest.pass("module id of exam found as '3'");
		}catch(AssertionError e) {
			getModuleIdTest.fail("failed to find total marks of exam");
		}
	}

	@Test
	public void idSetterTest() {
		
		parentTest = extent.createTest("Setting and getting ModuleExam id");
		ExtentTest setIdTest = parentTest.createNode("Set ModuleExam id");
		ExtentTest getIdTest = parentTest.createNode("Get ModuleExam id");

		ModuleExam exam = new ModuleExam();
		
		try{
			exam.setTestId((long) 5);
			setIdTest.pass("created exam and set id as '5'");
		}catch(Exception e) {
			setIdTest.fail("id of created exam not set");
		}
		
		try{
			assertTrue(exam.getTestId().equals((long) 5));
			getIdTest.pass("id of exam found as '5'");
		}catch(AssertionError e) {
			getIdTest.fail("failed to find id of exam");
		}
	}

}
