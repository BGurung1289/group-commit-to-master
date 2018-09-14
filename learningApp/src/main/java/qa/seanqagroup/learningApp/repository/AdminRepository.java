package qa.seanqagroup.learningApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import qa.seanqagroup.learningApp.model.Admin;

public interface AdminRepository extends JpaRepository<Admin,Long>{

}
