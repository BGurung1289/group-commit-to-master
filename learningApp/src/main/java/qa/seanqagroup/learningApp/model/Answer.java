package qa.seanqagroup.learningApp.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="answer")
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long answerId;
	
	private String answerContent;
	
	private Long testQuestionId;
	
	private boolean isCorrect;

	public Long getAnswerId() {
		return answerId;
	}

	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	public Long getTestQuestionId() {
		return testQuestionId;
	}

	public void setTestQuestionId(Long testQuestionId) {
		this.testQuestionId = testQuestionId;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	
	

}
