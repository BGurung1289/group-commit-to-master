package qa.seanqagroup.learningApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import qa.seanqagroup.learningApp.model.authentication.Session;

public interface SessionRepository extends JpaRepository<Session,Long>{

}
