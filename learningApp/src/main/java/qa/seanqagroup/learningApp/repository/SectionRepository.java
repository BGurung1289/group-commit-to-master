package qa.seanqagroup.learningApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qa.seanqagroup.learningApp.model.Section;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section,Long> {
    List<Section> getSectionsByModuleId(Long moduleId);

    Section getSectionBySectionId(Long sectionId);
}
