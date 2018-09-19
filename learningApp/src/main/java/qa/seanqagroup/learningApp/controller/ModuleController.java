package qa.seanqagroup.learningApp.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import qa.seanqagroup.learningApp.model.Module;
import qa.seanqagroup.learningApp.model.Section;
import qa.seanqagroup.learningApp.repository.ModuleRepository;
import qa.seanqagroup.learningApp.repository.SectionRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/module")
@CrossOrigin(origins = "http://localhost:3000")
public class ModuleController {

    @Autowired
    private ModuleRepository moduleRepo;

    @Autowired
    private SectionRepository sectionRepo;

    @GetMapping("/{moduleId}/getSections")
    public List<Section> getNumberOfSections(@PathVariable(value = "moduleId") Long moduleId) {
        // h'mta modulidt
        Module module = moduleRepo.getModuleByModuleId(moduleId);

        //h'mta samtliga sections som tillh;r modulen
        List<Section> sections = sectionRepo.getSectionsByModuleId(module.getModuleId());

        return sections;
    }

    @PostMapping("/add")
    public void createModule(Module module) {
        moduleRepo.save(module);
    }

    @GetMapping("/searchModule")
    public String getCourseIdName() {
        Gson gson = new Gson();
        return gson.toJson(moduleRepo.findAll());
    }

    @GetMapping("/trainerModules/{courseId}")
    public String getCoursesByTrainer(@PathVariable(value = "courseId") Long courseId) {
        List<Module> trainerCourses = new ArrayList<>();
        List<Module> modules = moduleRepo.findAll();
        for (Module eachCourse : modules) {
            if (eachCourse.getCourseId() == courseId) trainerCourses.add(eachCourse);
        }
        Gson gson = new Gson();
        return gson.toJson(trainerCourses);
    }
}
