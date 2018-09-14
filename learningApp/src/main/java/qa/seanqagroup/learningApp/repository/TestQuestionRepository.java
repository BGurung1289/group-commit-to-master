package qa.seanqagroup.learningApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import qa.seanqagroup.learningApp.model.TestQuestionModel;


public interface TestQuestionRepository extends JpaRepository<TestQuestionModel,Long> {

}
