package qa.seanqagroup.learningApp.foreignKeys;

import java.io.Serializable;

public class UserAdminForeignKey implements Serializable{

	private Long userId;
	private Long adminId;
	
	
	public UserAdminForeignKey() {
		super();
	}
	public UserAdminForeignKey(Long userId, Long adminId) {
		super();
		this.userId = userId;
		this.adminId = adminId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getAdminId() {
		return adminId;
	}
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	
	
}
