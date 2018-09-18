package qa.seanqagroup.learningApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qa.seanqagroup.learningApp.exceptions.ResourceNotFoundException;
import qa.seanqagroup.learningApp.model.User;
import qa.seanqagroup.learningApp.model.authentication.Session;
import qa.seanqagroup.learningApp.repository.SessionRepository;
import qa.seanqagroup.learningApp.repository.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("/sc")
public class SessionController {
	
	@Autowired
	SessionRepository sessionRepo;
	@Autowired
	UserRepository userRepo;
	
	@PostMapping("/startsession/{id}")
	public Session startSession(@PathVariable(value="id")Long userId) {
		User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("USER", "ID", userId));

		Session newSession = new Session();
		newSession.setUserId(user.getUserId());
		newSession.setUserType(user.getUserType());
		return sessionRepo.save(newSession);
	}
//	@GetMapping("/startsession")
//	public Session startSession() {
//		System.out.println("Hello");
//		System.out.println("Hello");
//
//		Session newSession = new Session();
////		newSession.setUserId(user.getUserId());
////		newSession.setUserType(user.getUserType());
//		return sessionRepo.save(newSession);
//	}

}
