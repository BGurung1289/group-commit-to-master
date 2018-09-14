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
import qa.seanqagroup.learningApp.model.Section;
import qa.seanqagroup.learningApp.repository.SectionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { LearningAppApplication.class })
@DataJpaTest
public class SectionTest {

	private static ExtentHtmlReporter htmlReporter;
	private static ExtentReports extent = new ExtentReports();
	private ExtentTest parentTest;
	private ExtentTest test;

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private SectionRepository sectionRepo;

	@BeforeClass
	public static void setUpBeforeClass() {
		htmlReporter = new ExtentHtmlReporter("C:\\Users\\Admin\\Desktop\\ExtentReports\\SectionTestReport.html");
		extent.attachReporter(htmlReporter);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		extent.flush();
	}

	@Test
	public void getSetTest() {
		Section section = new Section();
		section.setSectionName("For Loop");
		section.setSectionContent("There is enchanced and normal for loop");
		section.setModuleId(1);

		parentTest = extent.createTest("SET and GET Section Object");
		ExtentTest childTest = parentTest.createNode("GET Section Name");
		ExtentTest childTest2 = parentTest.createNode("GET content");
		ExtentTest childTest3 = parentTest.createNode("GET module ID");

		try {
			assertEquals("For Loop", section.getSectionName());
			childTest.pass(MarkupHelper.createLabel("Found Name", ExtentColor.BLUE));
		} catch (AssertionError e) {
			childTest.fail(MarkupHelper.createLabel("Failed to find name", ExtentColor.RED));
		}

		try {
			assertEquals("There is enchanced and normal for loop", section.getSectionContent());
			childTest2.pass(MarkupHelper.createLabel("Found Description", ExtentColor.BLUE));
		} catch (AssertionError e) {
			childTest2.fail(MarkupHelper.createLabel("Failed get description", ExtentColor.RED));
		}

		try {
			assertEquals(1, section.getModuleId());
			childTest3.pass(MarkupHelper.createLabel("Found module ID", ExtentColor.BLUE));
		} catch (AssertionError e) {
			childTest3.fail(MarkupHelper.createLabel("Failed to find module", ExtentColor.RED));
		}

	}

	@Test
	public void saveAndGetFromRepository() {
		Section section = new Section();
		section.setSectionName("For Loop");
		section.setSectionContent("There is enchanced and normal for loop");
		section.setModuleId(1);

		entityManager.persist(section);
		entityManager.flush();

		test = extent.createTest("Check Section object is saved to database");
		try {
			assertTrue(sectionRepo.findById(section.getSectionId()).isPresent());
			test.pass(MarkupHelper.createLabel("Found section in database", ExtentColor.GREEN));
		} catch (AssertionError e) {
			test.fail(MarkupHelper.createLabel("Failed to save to database", ExtentColor.RED));
		}
	}

}
