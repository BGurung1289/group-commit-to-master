package qa.seanqagroup.learningApp.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
	
	@Test
	public void testCon() {
		
		User testUser = new User();
		entityManager.persist(testUser);
		entityManager.flush();
		assertTrue(userRepo.findById(testUser.getUserId()).isPresent());
		
		
				
	}
	@Test
	public void testCon2() {
		
	
		User testUser2 = new User("a","a","p",E_UserType.LEARNER,"a@a.a");
		entityManager.persist(testUser2);
		entityManager.flush();
		assertTrue(userRepo.findById(testUser2.getUserId()).isPresent());
				
	}
	@Test
	public void findUsingEmail() {
		
	
		User testUser2 = new User("a","a","p",E_UserType.LEARNER,"a@a.a");
		entityManager.persist(testUser2);
		entityManager.flush();
		assertEquals("Person Not Real",userRepo.findByEmail(testUser2.getEmail()).getFirstName(),"a");
				
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
		
		
		assertEquals("Person Not Real",persistedUser.getFirstName(),"b");
		assertEquals("Person Not Real",persistedUser.getLastName(),"b");
		assertEquals("Person Not Real",persistedUser.getEmail(),"b@b.b");
		assertEquals("Person Not Real",persistedUser.getPassword(),"p");
		assertEquals("Person Not Real",persistedUser.getUserType(),E_UserType.LEARNER);




		
			}

}
