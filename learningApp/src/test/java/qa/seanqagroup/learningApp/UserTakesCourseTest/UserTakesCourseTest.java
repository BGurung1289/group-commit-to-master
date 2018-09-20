package qa.seanqagroup.learningApp.UserTakesCourseTest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import qa.seanqagroup.learningApp.model.UserTakesCourse;
import qa.seanqagroup.learningApp.repository.UserCourseRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserTakesCourseTest {

	@Autowired
	private TestEntityManager testManager;
	
	@Autowired
	private UserCourseRepository userCourseRepo;
	
	@Test
	public void findByUserIdTest() {		
		UserTakesCourse userCourse = new UserTakesCourse((long) 2, (long) 15, (long) 0);
		testManager.persist(userCourse);
		testManager.flush();
		
		assertNotNull(userCourseRepo.findByUserId(userCourse.getUserId()));
	}
	
	
	
}
