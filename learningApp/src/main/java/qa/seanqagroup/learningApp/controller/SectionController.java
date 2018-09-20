package qa.seanqagroup.learningApp.controller;

import com.google.gson.Gson;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import qa.seanqagroup.learningApp.model.Section;
import qa.seanqagroup.learningApp.model.SectionHasVideo;
import qa.seanqagroup.learningApp.model.Video;
import qa.seanqagroup.learningApp.repository.SectionHasVideoRepository;
import qa.seanqagroup.learningApp.repository.SectionRepository;
import qa.seanqagroup.learningApp.repository.VideoRepository;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/section")
public class SectionController {

    @Autowired
    private SectionRepository sectionRepo;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private SectionHasVideoRepository shvRepository;

    @GetMapping("/{sectionId}")
    public String getSection(@PathVariable(value = "sectionId") Long sectionId) {
		Section currentSection = sectionRepo.getSectionBySectionId(sectionId);
		
		JSONObject section = new JSONObject();
		
		section.put("id", currentSection.getSectionId());
		section.put("name", currentSection.getSectionName());
		section.put("content",  currentSection.getSectionContent());
		
		return section.toString();
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
    public void addYoutube(Video video, @RequestParam("sectionid") long sectionid,
                           @RequestParam("thisIsYoutube") long thisIsYoutube) {
        SectionHasVideo sectionHasVideo = new SectionHasVideo();
        System.out.println("IS YOUTUBE " + video.isYoutube());
        if (thisIsYoutube == 1) {
            video.setYoutube(true);
            System.out.println("IS YOUTUBE 2 " + video.isYoutube());
        }
        videoRepository.save(video);

        long videoid = videoRepository.findAll().get(videoRepository.findAll().size() - 1).getVideoId();

        sectionHasVideo.setSectionId(sectionid);
        sectionHasVideo.setVideoId(videoid);
        shvRepository.save(sectionHasVideo);
    }

    @GetMapping("/searchSection")
    public String getCourseIdName() {
        Gson gson = new Gson();
        return gson.toJson(sectionRepo.findAll());
    }

    @GetMapping("/trainerSection/{moduleId}")
    public String getCoursesByTrainer(@PathVariable(value = "moduleId") Long moduleId) {
        List<Section> trainerCourses = new ArrayList<>();
        List<Section> sections = sectionRepo.findAll();
        for (Section eachCourse : sections) {
            if (eachCourse.getModuleId() == moduleId) trainerCourses.add(eachCourse);
        }
        Gson gson = new Gson();
        return gson.toJson(trainerCourses);
    }
}
