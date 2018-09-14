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
@CrossOrigin(origins = "http://localhost:3000")
public class SectionController {

	@Autowired
	private SectionRepository sectionRepo;
	
	@Autowired
	private VideoRepository videoRepository;

	@Autowired
	private SectionHasVideoRepository shvRepository;
	
	@GetMapping("/{sectionId}")
	public Section getSection(@PathVariable(value = "sectionId") Long sectionId) {
		Section section = sectionRepo.getSectionBySectionId(sectionId);
		return section;
	}
	
	@PostMapping("/add")
	public String createSection(Section section) {
		sectionRepo.save(section);
		long id = sectionRepo.findAll().get(sectionRepo.findAll().size() - 1).getSectionId();

		System.out.println("section made");
		Gson gson = new Gson();
		String store = gson.toJson("{\"sectionid\":\"" + id + "\"}");
		return store;
	}

	@PostMapping("/youtube")
	public void addYoutube(Video video, @RequestParam("sectionid") long sectionid) {
		SectionHasVideo sectionHasVideo = new SectionHasVideo();

		video.setYoutube(true);
		videoRepository.save(video);

		long videoid = videoRepository.findAll().get(videoRepository.findAll().size() - 1).getVideoId();

		sectionHasVideo.setSectionId(sectionid);
		sectionHasVideo.setVideoId(videoid);
		shvRepository.save(sectionHasVideo);
	}
}
