package qa.seanqagroup.learningApp.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name= "Test_Question")
public class TestQuestionModel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long testQuestionId;
	
	private String questionContent;
	
	// @NotBlank  
	private long testId;

	public long getTestQuestionId() {
		return testQuestionId;
	}

	public void setTestQuestionId(long testQuestionId) {
		this.testQuestionId = testQuestionId;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public long getTestId() {
		return testId;
	}

	public void setTestId(long testId) {
		this.testId = testId;
	}
	
}

// 	public TestQuestionModel (long testQuestionId, String questionContent, long testId) {
//super();
//this.testQuestionId = testQuestionId;
//this.questionContent = questionContent;
//this.testId = testId;
