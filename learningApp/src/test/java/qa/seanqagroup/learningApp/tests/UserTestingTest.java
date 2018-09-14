package qa.seanqagroup.learningApp.tests;

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
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import qa.seanqagroup.learningApp.LearningAppApplication;
import qa.seanqagroup.learningApp.model.User;
import qa.seanqagroup.learningApp.model.enums.E_UserType;
import qa.seanqagroup.learningApp.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {LearningAppApplication.class})

@DataJpaTest
public class UserTestingTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository userRepo;
	
	private static ExtentHtmlReporter htmlReporter;
	
	private static ExtentReports extent = new ExtentReports();
	
	private ExtentTest test;
	
	private static final String className = "UserTesting.html";
	@BeforeClass
	public static void setUpBeforeClass() {
		htmlReporter = new ExtentHtmlReporter("C:\\Users\\Admin\\Desktop\\"+className);
		extent.attachReporter(htmlReporter);
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception{
		extent.flush();
	}
	
	@Test
	public void testCon() {
		
		User testUser = new User();
		entityManager.persist(testUser);
		entityManager.flush();
		test = extent.createTest("Test User Creation and retrieval from database by ID");
		try {
		assertTrue(userRepo.findById(testUser.getUserId()).isPresent());
		test.pass(MarkupHelper.createLabel("User(default constructor) found by ID", ExtentColor.GREEN));
		}catch(AssertionError e) {
			test.fail(MarkupHelper.createLabel("User Not found by ID", ExtentColor.RED));

		}
		finally {
		entityManager.clear();
		}

		
				
	}
	@Test
	public void testCon2() {
		
	
		User testUser2 = new User("a","a","p",E_UserType.LEARNER,"a@a.a");
		entityManager.persist(testUser2);
		entityManager.flush();
		test = extent.createTest("Test User Creation 2");
		try {
		assertTrue(userRepo.findById(testUser2.getUserId()).isPresent());
		test.pass(MarkupHelper.createLabel("User found by ID", ExtentColor.GREEN));
		}catch(AssertionError e) {
			test.fail(MarkupHelper.createLabel("User Not found by ID", ExtentColor.RED));

		}
		finally {
		entityManager.clear();
		}
				
	}
	@Test
	public void findUsingEmail() {
		
	
		User testUser2 = new User("a","a","p",E_UserType.LEARNER,"a@a.a");
		entityManager.persist(testUser2);
		entityManager.flush();
		test = extent.createTest("Test User retrieval from database by email");
		try {
		assertEquals("Person Not Real",userRepo.findByEmail(testUser2.getEmail()).getFirstName(),"a");
		test.pass(MarkupHelper.createLabel("User found by Email", ExtentColor.GREEN));
		}catch(AssertionError e) {
			test.fail(MarkupHelper.createLabel("User Not found by Email", ExtentColor.RED));

		}
		finally {
			entityManager.clear();
		}

				
	}
	@Test
	public void testGetSet() {
		
	
		User testUser = new User();
		testUser.setFirstName("b");
		testUser.setLastName("b");
		testUser.setEmail("b@b.b");
		testUser.setPassword("p");
		testUser.setUserType(E_UserType.LEARNER);
		entityManager.persist(testUser);
		entityManager.flush();
		
		User persistedUser = userRepo.findByEmail("b@b.b");
		test = extent.createTest("Test User getters and setters");
		try {
		assertEquals("Person Not Real",persistedUser.getFirstName(),"b");
		assertEquals("Person Not Real",persistedUser.getLastName(),"b");
		assertEquals("Person Not Real",persistedUser.getEmail(),"b@b.b");
		assertEquals("Person Not Real",persistedUser.getPassword(),"p");
		assertEquals("Person Not Real",persistedUser.getUserType(),E_UserType.LEARNER);
		test.pass(MarkupHelper.createLabel("User getters and setters work", ExtentColor.GREEN));
		}catch(AssertionError e) {
			test.fail(MarkupHelper.createLabel("User getters and setters don't work", ExtentColor.RED));

		}
		finally {
			entityManager.clear();
		}


}
//		@Test
//		public void testUserCreationController() {
//			 UserCreationController ucc = new UserCreationController();
//			 User testUser = new User();
//			 entityManager.persist(testUser);
//			 entityManager.flush();
//			 
//			 Long id = new Long(10);
//			 User user1 = ucc.getUserById(id);
//			 User user2 = userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("USER", "ID", id));
//			 assertTrue(user1.getUserId().equals(user2.getUserId()));
//			 
//			
//		}

}
