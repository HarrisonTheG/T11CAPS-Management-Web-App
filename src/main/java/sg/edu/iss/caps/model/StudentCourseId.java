package sg.edu.iss.caps.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StudentCourseId implements Serializable{
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "student_id")
	private int studentId;
	
	@Column(name = "course_id")
	private int courseId;

	public StudentCourseId(int studentId, int courseId) {
		super();
		this.studentId = studentId;
		this.courseId = courseId;
	}

	public StudentCourseId() {
		super();
	}

	public int getStudentId() {
		return studentId;
	}

	public int getCourseId() {
		return courseId;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + courseId;
		result = prime * result + studentId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentCourseId other = (StudentCourseId) obj;
		if (courseId != other.courseId)
			return false;
		if (studentId != other.studentId)
			return false;
		return true;
	}
	
	

}
