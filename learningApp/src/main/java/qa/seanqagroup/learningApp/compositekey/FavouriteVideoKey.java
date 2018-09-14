package qa.seanqagroup.learningApp.compositekey;

import java.io.Serializable;

public class FavouriteVideoKey implements Serializable {
	private long userId;
	private long videoId;

	public FavouriteVideoKey() {
	}

	public FavouriteVideoKey(long userId, long videoId) {
		this.userId = userId;
		this.videoId = videoId;
	}

}
