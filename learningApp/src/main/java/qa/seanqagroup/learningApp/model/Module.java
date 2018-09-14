package qa.seanqagroup.learningApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Module")
public class Module {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long moduleId;
	
	@NotNull
	private String moduleName;
	
	private String moduleDescription;
	
	@NotNull
	private long courseId;

	public long getModuleId() {
		return moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleDescription() {
		return moduleDescription;
	}

	public void setModuleDescription(String moduleDescription) {
		this.moduleDescription = moduleDescription;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	
}
