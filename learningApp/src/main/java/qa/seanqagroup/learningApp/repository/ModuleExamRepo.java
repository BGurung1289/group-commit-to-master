package qa.seanqagroup.learningApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import qa.seanqagroup.learningApp.model.ModuleExam;

@Repository
public interface ModuleExamRepo extends JpaRepository<ModuleExam, Long>{

}
