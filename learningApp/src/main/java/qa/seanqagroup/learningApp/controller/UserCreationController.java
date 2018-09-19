package qa.seanqagroup.learningApp.controller;

import javax.validation.Valid;

import org.json.JSONException;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;
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
	public String checkDetails(User user) throws JSONException{
		JSONObject obj = new JSONObject();
		try{
			User match = userRepo.findByEmail(user.getEmail());
			System.out.println(match.getPassword());
			System.out.println(user.getPassword());
			if(BCrypt.checkpw(user.getPassword(), match.getPassword())) {
				obj.put("result", "success");
				obj.put("name", match.getFirstName());
				obj.put("id", match.getUserId());
				obj.put("type", match.getUserType());
				return obj.toString();
			}
		}catch(Exception e) {
			obj.put("result", "fail");
		}
		
		return obj.toString();
	}
	
	
	@PostMapping("/register")
	public String registerUser(User user) throws JSONException{
		JSONObject object = new JSONObject();
		user.setUserType(E_UserType.LEARNER);
		String hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
		user.setPassword(hash);
		userRepo.save(user);
		try {
			object.put("result", "successful");
		} catch (Exception e) {
			object.put("result", "fail");
		}
		return object.toString();
	}
}
