package qa.seanqagroup.learningApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import qa.seanqagroup.learningApp.model.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module,Long>{

}
