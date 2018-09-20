package qa.seanqagroup.learningApp.compositekey;

import java.io.Serializable;

public class UserHasExamKey implements Serializable{
	private Long userId;
    private Long testId;
    
    public UserHasExamKey() {};
    
	public UserHasExamKey(Long userId, Long testId) {
		this.userId = userId;
		this.testId = testId;
	}
		   
}
