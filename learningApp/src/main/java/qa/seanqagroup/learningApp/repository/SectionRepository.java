package qa.seanqagroup.learningApp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import qa.seanqagroup.learningApp.model.Module;
import qa.seanqagroup.learningApp.model.Section;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
	//Page<Section> getSectionsByModule(Module module, Pageable pageable);

	List<Section> getSectionsByModuleId(Long moduleId);
	
	Section getSectionBySectionId(Long sectionId);
}