package qa.seanqagroup.learningApp.integration;

import static org.junit.Assert.*;

import org.junit.Test;


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
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import qa.seanqagroup.learningApp.LearningAppApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LearningAppApplication.class})
@AutoConfigureMockMvc
public class SessionControllerTest {

	@Autowired
	private MockMvc mvc;
	
private static ExtentHtmlReporter htmlReporter;
	
	private static ExtentReports extent = new ExtentReports();
	
	private ExtentTest test;
	
	private static final String className = "SessionControllerExtentReport.html";
	
	
	@Test
	public void createNewSession() throws Exception {
		int testId = 1;
		mvc.perform(MockMvcRequestBuilders.post("/sc/startsession/"+ testId ))
		.andExpect(status().isOk());	
	}
	
//	@BeforeClass
//	public static void setUpBeforeClass() {
//		
//		htmlReporter = new ExtentHtmlReporter("C:\\Users\\Admin\\Desktop\\"+className);
//		extent.attachReporter(htmlReporter);
//	}
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception{
//		extent.flush();
//	}
}

