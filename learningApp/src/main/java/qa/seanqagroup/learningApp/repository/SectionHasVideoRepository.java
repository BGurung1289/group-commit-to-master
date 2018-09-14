package qa.seanqagroup.learningApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import qa.seanqagroup.learningApp.model.SectionHasVideo;

@Repository
public interface SectionHasVideoRepository extends JpaRepository<SectionHasVideo,Long>{

}
