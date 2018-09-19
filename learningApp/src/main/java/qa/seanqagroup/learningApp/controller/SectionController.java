package qa.seanqagroup.learningApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import qa.seanqagroup.learningApp.model.Section;
import qa.seanqagroup.learningApp.model.SectionHasVideo;
import qa.seanqagroup.learningApp.model.Video;
import qa.seanqagroup.learningApp.repository.SectionHasVideoRepository;
import qa.seanqagroup.learningApp.repository.SectionRepository;
import qa.seanqagroup.learningApp.repository.VideoRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SectionController {

	@Autowired
	private SectionRepository sectionRepository;

	@Autowired
	private VideoRepository videoRepository;

	@Autowired
	private SectionHasVideoRepository shvRepository;

	@PostMapping("/section/add")
	public String createSection(Section section) {
		sectionRepository.save(section);
		long id = sectionRepository.findAll().get(sectionRepository.findAll().size() - 1).getSectionId();

		System.out.println("section made");
		Gson gson = new Gson();
		String store = gson.toJson("{\"sectionid\":\"" + id + "\"}");
		return store;
	}

	@PostMapping("/section/youtube")
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
