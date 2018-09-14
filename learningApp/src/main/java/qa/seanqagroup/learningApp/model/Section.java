package qa.seanqagroup.learningApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Section")
public class Section {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long sectionId;

	@NotNull
	private String sectionName;

	private String sectionContent;

	private long moduleId;

	public long getSectionId() {
		return sectionId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public long getModuleId() {
		return moduleId;
	}

	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}

	public String getSectionContent() {
		return sectionContent;
	}

	public void setSectionContent(String sectionContent) {
		this.sectionContent = sectionContent;
	}
}
