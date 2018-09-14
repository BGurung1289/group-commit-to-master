package qa.seanqagroup.learningApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qa.seanqagroup.learningApp.model.Section;
import qa.seanqagroup.learningApp.repository.SectionRepository;

@RestController
@RequestMapping("/section")
public class SectionController {

	@Autowired
	private SectionRepository sectionRepo;
	
	@GetMapping("/{sectionId}")
	public Section getSection(@PathVariable(value = "sectionId") Long sectionId) {
		Section section = sectionRepo.getSectionBySectionId(sectionId);
		return section;
	}
}
