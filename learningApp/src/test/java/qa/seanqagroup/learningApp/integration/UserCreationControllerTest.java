package qa.seanqagroup.learningApp.integration;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import qa.seanqagroup.learningApp.LearningAppApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { LearningAppApplication.class })
@AutoConfigureMockMvc
public class UserCreationControllerTest {
	
	private static ExtentHtmlReporter htmlReporter;
	private static ExtentReports extent = new ExtentReports();
	private ExtentTest test;


	@Autowired
	private MockMvc mvc;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		htmlReporter = new ExtentHtmlReporter("C:\\Users\\Admin\\Desktop\\loginControllerTest.html");
		extent.attachReporter(htmlReporter);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		extent.flush();
	}

	@Test
	public void checkDTest() throws Exception {
		test = extent.createTest("logging in POST");
		try {
//			mvc.perform(MockMvcRequestBuilders.post("/ucc/login")
//					.contentType(MediaType.APPLICATION_JSON)
//                    .content("{\"email\" : \"b@b.b\", \"password\":\"pass\"}")
//					).andExpect(status().isOk());
			mvc.perform(MockMvcRequestBuilders.post("/ucc/login")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("email","fb@fb.fb")
					.param("password", "pass")
					).andExpect(status().isOk());
			test.pass(MarkupHelper.createLabel("Logged in with details : b@b.b :pass", ExtentColor.GREEN));
		}catch(AssertionError e) {
			test.fail(MarkupHelper.createLabel("Not logged in with details : b@b.b :pass", ExtentColor.RED));
		}
	}
	
	@Test
	public void testRegister() throws Exception {
		test = extent.createTest("Registering in Post");
		try {
			mvc.perform(MockMvcRequestBuilders.post("/ucc/register")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("email", "x@x.x")
                    .param("firstName", "Bibek")
                    .param("lastName", "Gurung")
                    .param("password", "pw")
					).andExpect(status().isOk());
			test.pass(MarkupHelper.createLabel("Registered with details : chi@chi.chi :pass :chi :chi", ExtentColor.GREEN));
		}catch(AssertionError e) {
			test.fail(MarkupHelper.createLabel("Not Registered with details : chi@chi.chi :pass :chi :chi", ExtentColor.RED));
		}
	}

}
