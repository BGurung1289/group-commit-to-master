package qa.seanqagroup.learningApp.compositekey;

import java.io.Serializable;

public class SectionHasVideoKey implements Serializable {
	private long sectionId;
	private long videoId;
	
	public SectionHasVideoKey() {}
	
	public SectionHasVideoKey(long sectionId, long videoId) {
		this.sectionId = sectionId;
		this.videoId = videoId;
	}

}
