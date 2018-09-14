package qa.seanqagroup.learningApp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import qa.seanqagroup.learningApp.compositekey.FavouriteVideoKey;

@Entity
@Table(name = "Favourite_Video")
@IdClass(FavouriteVideoKey.class)
public class FavouriteVideo {
	
	@Id
	@NotNull
	private long userId;
	
	@Id
	@NotNull
	private long videoId;


	public long getVideoId() {
		return videoId;
	}

	public void setVideoId(long videoId) {
		this.videoId = videoId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
}
