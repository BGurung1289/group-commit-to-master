package qa.seanqagroup.learningApp.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import qa.seanqagroup.learningApp.compositekey.UserTakesCourseKey;


@Entity
@Table(name = "user_takes_course")
@IdClass(UserTakesCourseKey.class)
public class UserTakesCourse implements Serializable {

	@Id
	@NotNull
	private Long userId;
	
	@Id
	@NotNull
	private Long courseId;
	
	@NotNull
	private Long isCompleted;
	
	public UserTakesCourse() {}
	
	public UserTakesCourse(Long userId, Long courseId, Long isCompleted) {
		this.userId = userId;
		this.courseId = courseId;
		this.isCompleted = isCompleted;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(Long isCompleted) {
		this.isCompleted = isCompleted;
	}
}
