package qa.seanqagroup.learningApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import qa.seanqagroup.learningApp.model.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer,Long>{

}
