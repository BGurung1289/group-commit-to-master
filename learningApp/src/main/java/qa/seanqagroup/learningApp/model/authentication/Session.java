package qa.seanqagroup.learningApp.model.authentication;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import qa.seanqagroup.learningApp.model.enums.E_UserType;

@Entity
public class Session implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long sessionId;
	
	private Long userId;
//	private Date timeCreated;
	private Timestamp timeExpires;
	private Timestamp timeCreated;
	private E_UserType userType;
	
	public Session() {
		super();
//		timeCreated = new Date(System.currentTimeMillis());
		Calendar temp = Calendar.getInstance();
		timeCreated = new Timestamp(temp.getTimeInMillis());
				temp.add(Calendar.HOUR_OF_DAY, 1);
		timeExpires = new Timestamp(temp.getTimeInMillis());
		
	}

	
	public Long getSessionId() {
		return sessionId;
	}


	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


//	public Date getTimeCreated() {
//		return timeCreated;
//	}
//
//	public void setTimeCreated(Date timeCreated) {
//		this.timeCreated = timeCreated;
//	}

	
	public Timestamp getTimeCreated() {
		return timeCreated;
	}


	public void setTimeCreated(Timestamp timeCreated) {
		this.timeCreated = timeCreated;
	}

	
//	public Date getTimeExpires() {
//		return timeExpires;
//	}
//
//	public void setTimeExpires(Date timeExpires) {
//		this.timeExpires = timeExpires;
//	}

	public Timestamp getTimeExpires() {
		return timeExpires;
	}


	public void setTimeExpires(Timestamp timeExpires) {
		this.timeExpires = timeExpires;
	}


	public E_UserType getUserType() {
		return userType;
	}

	public void setUserType(E_UserType userType) {
		this.userType = userType;
	}
	
	
	
}
