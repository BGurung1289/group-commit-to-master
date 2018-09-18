package qa.seanqagroup.learningApp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qa.seanqagroup.learningApp.exceptions.ResourceAlreadyExistsException;
import qa.seanqagroup.learningApp.exceptions.ResourceNotFoundException;
import qa.seanqagroup.learningApp.model.Admin;
import qa.seanqagroup.learningApp.model.Trainer;
import qa.seanqagroup.learningApp.model.User;
import qa.seanqagroup.learningApp.model.enums.E_UserType;
import qa.seanqagroup.learningApp.repository.TrainerRepository;
import qa.seanqagroup.learningApp.repository.UserRepository;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/tc")
public class TrainerController {

	@Autowired
	UserRepository userRepo;
	@Autowired
	TrainerRepository trainerRepo;
	
	

	

	
	
	
	

	
	@PostMapping("/register/{id}")
	public Trainer updateToTrainer(@PathVariable(value = "id")Long userId) {
		User tempUser = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("USER", "ID" , userId));

		if(tempUser.getUserType()!=E_UserType.LEARNER)
			throw new ResourceAlreadyExistsException("USER", "ID AND TYPE", userId+" : "+tempUser.getUserType());
		
		Trainer newTrainer = new Trainer();
		newTrainer.setUserId(tempUser.getUserId());
		tempUser.setUserType(E_UserType.TRAINER);
		newTrainer.setTrainerManagerId(new Long(0)); 
		userRepo.save(tempUser);

		return trainerRepo.save(newTrainer);
	}
	
}
