package qa.seanqagroup.learningApp.compositekey;

import java.io.Serializable;

public class UserTakesCourseKey implements Serializable {
	private Long userId;
	private Long courseId;
	
	public UserTakesCourseKey() {};
	
	public UserTakesCourseKey(Long userId, Long courseId) {
		this.userId = userId;
		this.courseId = courseId;
	}

}
