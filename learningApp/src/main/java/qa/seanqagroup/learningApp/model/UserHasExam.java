package qa.seanqagroup.learningApp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import qa.seanqagroup.learningApp.compositekey.UserHasExamKey;

@Entity
@Table(name= "user_has_test")
@EntityListeners(AuditingEntityListener.class)
@IdClass(UserHasExamKey.class)
public class UserHasExam implements Serializable{
	
	@Id
	@NotNull
	private Long userId;
	
	@Id 
	@NotNull
	private Long testId;
	
	@NotNull
	private boolean isCompleted;
	
	private Long marksCorrect;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public boolean isCompleted() {
		return isCompleted;
	} 

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public Long getMarksCorrect() {
		return marksCorrect;
	}

	public void setMarksCorrect(Long marksCorrect) {
		this.marksCorrect = marksCorrect;
	}
	
	
}
