package qa.seanqagroup.learningApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import qa.seanqagroup.learningApp.model.UserHasExam;

@Repository
public interface UserHasExamRepo extends JpaRepository<UserHasExam, Long>{

}
