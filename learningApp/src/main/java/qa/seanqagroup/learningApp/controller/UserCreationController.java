package qa.seanqagroup.learningApp.controller;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qa.seanqagroup.learningApp.exceptions.ResourceNotFoundException;
import qa.seanqagroup.learningApp.model.User;
import qa.seanqagroup.learningApp.model.enums.E_UserType;
import qa.seanqagroup.learningApp.repository.UserRepository;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/ucc")
public class UserCreationController {

	@Autowired
	UserRepository userRepo;
	
	
	
	@GetMapping("/u/id/{id}")
	public User getUserById(@PathVariable(value="id") Long userID) {
		User user = userRepo.findById(userID).orElseThrow(()-> new ResourceNotFoundException("USER", "ID", userID));
		return user;
	}
	@GetMapping("/u/e/{e}")
	public User getUserByEmail(@PathVariable(value="e") String email) {
		User user = userRepo.findByEmail(email);
		return user;
	}
	
	@PostMapping("/login")
	public String checkDetails(User user){
		JSONObject obj = new JSONObject();
		for (User everyone : userRepo.findAll()) {
			System.out.println(everyone.getEmail());
			if(everyone.getEmail().equals(user.getEmail())) {
				if(everyone.getPassword().equals(user.getPassword())){
					obj.put("name",everyone.getFirstName());
					obj.put("id", everyone.getUserId());
					obj.put("type", everyone.getUserType());
					System.out.println("PASS RETURN");
					return obj.toString();
				}
			}
		}
		System.out.println("FAIL RETURN");
		obj.put("result", "fail");
		return obj.toString();
}
	
	
	
	
	@PostMapping("/register")
	public User registerUser(@Valid @RequestBody User user){
		user.setUserType(E_UserType.LEARNER);
		return userRepo.save(user);
	}
	
}
