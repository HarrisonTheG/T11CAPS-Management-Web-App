package sg.edu.iss.caps.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.hibernate.validator.constraints.Range;

@Entity
public class Student_Course {
	
    @EmbeddedId
    private StudentCourseId id;
	
	@Column @Range(min=0, max=100)
	private double grade;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@MapsId("studentId")
	private Student student;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@MapsId("courseId")
	private Course course;
	

	public Student_Course() {
		super();
	}

	public Student_Course(double grade, Student student, Course course) {
		this.grade = grade;
		this.student = student;
		this.course = course;
		this.id = new StudentCourseId(student.getId(), course.getId());
	}

	public StudentCourseId getId() {
		return id;
	}

	public void setId(StudentCourseId id) {
		this.id = id;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}


	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Student_Course other = (Student_Course) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	
	

}
