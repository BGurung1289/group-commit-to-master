package qa.seanqagroup.learningApp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import qa.seanqagroup.learningApp.compositekey.SectionHasVideoKey;

@Entity
@Table(name = "Section_has_Video")
@IdClass(SectionHasVideoKey.class)
public class SectionHasVideo {

	@Id
	@NotNull
	private long sectionId;
	
	@Id
	@NotNull
	private long videoId;

	public long getSectionId() {
		return sectionId;
	}

	public void setSectionId(long sectionId) {
		this.sectionId = sectionId;
	}

	public long getVideoId() {
		return videoId;
	}

	public void setVideoId(long videoId) {
		this.videoId = videoId;
	}
}
