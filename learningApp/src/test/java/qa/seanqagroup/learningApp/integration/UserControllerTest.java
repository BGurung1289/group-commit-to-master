package qa.seanqagroup.learningApp.integration;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import qa.seanqagroup.learningApp.LearningAppApplication;
import qa.seanqagroup.learningApp.repository.UserRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LearningAppApplication.class})
@AutoConfigureMockMvc
public class UserControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void loginToDatabase() throws Exception{
		mvc.perform(MockMvcRequestBuilders.post("/ucc/login")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("email","b@b.b")
				.param("password", "pass")
				) 
		.andExpect(status() 
				.isOk()); 
	}
	
	
}

