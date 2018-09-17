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
		exam.setTestDescription("testing the testing");
		exam.setTotalMarks((long) 10);
		exam.setModuleId((long) 3);
		try {
			entityManager.persist(exam);
			entityManager.flush();
			createTest.pass("created exam");
		} catch (Exception e) {
			findTest.fail("exam not created");
		}
		try {
			assertTrue(moduleExamRepo.findById(exam.getTestId()).isPresent());
			findTest.pass("found exam");
		} catch (AssertionError e) {
			findTest.fail("exam not found");
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
			setNameTest.pass("name set");
		}catch(Exception e) {
			setNameTest.fail("name not set");
		}
		
		try{
			assertTrue(exam.getTestName().equals("test exam"));
			getNameTest.pass("name found");
		}catch(AssertionError e) {
			getNameTest.fail("name not found");
		}
	}

	@Test
	public void descGetterTest() {
		
		parentTest = extent.createTest("Setting and getting ModuleExam description");
		ExtentTest setDescTest = parentTest.createNode("Set ModuleExam description");
		ExtentTest getDescTest = parentTest.createNode("Get ModuleExam description");

		ModuleExam exam = new ModuleExam();
		
		try{
			exam.setTestDescription("testing the test");
			setDescTest.pass("description set");
		}catch(Exception e) {
			setDescTest.fail("description not set");
		}
		
		try{
			assertTrue(exam.getTestDescription().equals("testing the test"));
			getDescTest.pass("description found");
		}catch(AssertionError e) {
			getDescTest.fail("description not found");
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
			setMarkTest.pass("total marks set");
		}catch(Exception e) {
			setMarkTest.fail("total marks not set");
		}
		
		try{
			assertTrue(exam.getTotalMarks().equals((long) 10));
			getMarkTest.pass("total marks found");
		}catch(AssertionError e) {
			getMarkTest.fail("total marks not found");
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
			setModuleIdTest.pass("module id set");
		}catch(Exception e) {
			setModuleIdTest.fail("module id not set");
		}
		
		try{
			assertTrue(exam.getModuleId().equals((long) 3));
			getModuleIdTest.pass("module id found");
		}catch(AssertionError e) {
			getModuleIdTest.fail("module id not found");
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
			setIdTest.pass("id set");
		}catch(Exception e) {
			setIdTest.fail("id not set");
		}
		
		try{
			assertTrue(exam.getTestId().equals((long) 5));
			getIdTest.pass("id found");
		}catch(AssertionError e) {
			getIdTest.fail("id not found");
		}
	}

}
