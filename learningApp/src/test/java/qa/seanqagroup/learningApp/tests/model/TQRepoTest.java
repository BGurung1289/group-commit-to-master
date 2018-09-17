//package qa.seanqagroup.learningApp.tests.model;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import qa.seanqagroup.learningApp.LearningAppApplication;
//import qa.seanqagroup.learningApp.model.TestQuestionModel;
//import qa.seanqagroup.learningApp.repository.TestQuestionRepository;
//
//import static org.junit.Assert.assertTrue;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = { LearningAppApplication.class})
//
//@DataJpaTest
//public class TQRepoTest {
//
//	@Autowired
//	private TestEntityManager entityManager;
//	
//	@Autowired
//	private TestQuestionRepository tQRepo;
//	
////	@Test
////	public void retrieveByIdTest() {
////		TestQuestionModel model1 = new TestQuestionModel(1, "Red lorry or yellow lorry?", 1);
////		entityManager.persist(model1);
////		entityManager.flush();
////		assertTrue(tQRepo.findById(model1.getTestId()).isPresent());
////	}
//	
//}
