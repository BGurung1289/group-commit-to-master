package qa.seanqagroup.learningApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import qa.seanqagroup.learningApp.model.Answer;

@Repository
public interface AnswerRepo extends JpaRepository<Answer, Long>{

}
