package qa.seanqagroup.learningApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Trainer {
	private Long userId;
	private Long trainerManagerId;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long trainerId;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getTrainerId() {
		return trainerId;
	}
	public void setTrainerId(Long trainerId) {
		this.trainerId = trainerId;
	}
	public Long getTrainerManagerId() {
		return trainerManagerId;
	}
	public void setTrainerManagerId(Long trainerManagerId) {
		this.trainerManagerId = trainerManagerId;
	}
	
}
