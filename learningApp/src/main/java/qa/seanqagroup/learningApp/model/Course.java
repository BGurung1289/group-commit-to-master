package qa.seanqagroup.learningApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Course")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long courseId;
	
	@NotNull
	private String courseName;
	
	private String courseDescription;
	
	@NotNull
	private long madeByTrainerId;

	public long getCourseId() {
		return courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public long getTrainerId() {
		return madeByTrainerId;
	}

	public void setTrainerId(long madeByTrainerId) {
		this.madeByTrainerId = madeByTrainerId;
	}
}
