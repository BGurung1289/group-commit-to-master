package qa.seanqagroup.learningApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TrainerManager {
	private Long userId;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long trainerManagerId;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getTrainerManagerId() {
		return trainerManagerId;
	}
	public void setTrainerManagerId(Long trainerManagerId) {
		this.trainerManagerId = trainerManagerId;
	}
	
	
	
}
