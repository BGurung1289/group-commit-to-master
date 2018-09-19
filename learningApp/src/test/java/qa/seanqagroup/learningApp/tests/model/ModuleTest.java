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
import qa.seanqagroup.learningApp.model.Module;
import qa.seanqagroup.learningApp.repository.ModuleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { LearningAppApplication.class })
@DataJpaTest
public class ModuleTest {

	private static ExtentHtmlReporter htmlReporter;
	private static ExtentReports extent = new ExtentReports();
	private ExtentTest parentTest;
	private ExtentTest test;

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ModuleRepository moduleRepository;

	@BeforeClass
	public static void setUpBeforeClass() {
		htmlReporter = new ExtentHtmlReporter("C:\\Users\\Admin\\Desktop\\ExtentReports\\ModuleTestReport.html");
		extent.attachReporter(htmlReporter);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		extent.flush();
	}

	@Test
	public void getSetTest() {
		Module module = new Module();
		module.setModuleName("Java");
		module.setModuleDescription("Learn Java 8");
		module.setCourseId(1);

		parentTest = extent.createTest("SET and GET Module Object");
		ExtentTest childTest = parentTest.createNode("GET Module Name");
		ExtentTest childTest2 = parentTest.createNode("GET Description");
		ExtentTest childTest3 = parentTest.createNode("GET Course ID");

		try {
			assertEquals("Java", module.getModuleName());
			childTest.pass(MarkupHelper.createLabel("Found Name: Java", ExtentColor.BLUE));
		} catch (AssertionError e) {
			childTest.fail(MarkupHelper.createLabel("Failed to find name: Java", ExtentColor.RED));
		}

		try {
			assertEquals("Learn Java 8", module.getModuleDescription());
			childTest2.pass(MarkupHelper.createLabel("Found Description", ExtentColor.BLUE));
		} catch (AssertionError e) {
			childTest2.fail(MarkupHelper.createLabel("Failed get description", ExtentColor.RED));
		}

		try {
			assertEquals(1, module.getCourseId());
			childTest3.pass(MarkupHelper.createLabel("Found course ID: 1", ExtentColor.BLUE));
		} catch (AssertionError e) {
			childTest3.fail(MarkupHelper.createLabel("Failed to find course: 1", ExtentColor.RED));
		}

	}

	@Test
	public void saveAndGetFromRepository() {
		Module module = new Module();
		module.setModuleName("Java");
		module.setModuleDescription("Learn Java 8");
		module.setCourseId(1);

		entityManager.persist(module);
		entityManager.flush();

		test = extent.createTest("Check Module object is saved to database");
		try {
			assertTrue(moduleRepository.findById(module.getModuleId()).isPresent());
			test.pass(MarkupHelper.createLabel("Found module: moduleName: Java, description:Learn Java 8, courseID:1", ExtentColor.GREEN));
		} catch (AssertionError e) {
			test.fail(MarkupHelper.createLabel("Failed to save: moduleName: Java, description:Learn Java 8, courseID:1", ExtentColor.RED));
		}
	}

}
