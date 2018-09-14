package qa.seanqagroup.learningApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Video")
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long videoId;
	
	@NotNull
	private String videoUrl;
	
	private String videoName;
	
	private boolean isYoutube;
	
	private long trainerId;

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public long getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(long trainerId) {
		this.trainerId = trainerId;
	}

	public long getVideoId() {
		return videoId;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public boolean isYoutube() {
		return isYoutube;
	}

	public void setYoutube(boolean isYoutube) {
		this.isYoutube = isYoutube;
	}
}
