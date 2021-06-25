package sg.edu.iss.caps.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.hibernate.validator.constraints.Range;

@Entity
public class Student_Course {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	
	@Column @Range(min=0, max=100)
	private double grade;
	
	@ManyToOne (fetch = FetchType.LAZY)
	private User user;
	
	@ManyToOne (fetch = FetchType.LAZY)
	private Course course;
	
	public Student_Course() {
		super();
	}

	public Student_Course(@Range(min = 0, max = 100) double grade, User user, Course course) {
		super();
		this.grade = grade;
		this.user = user;
		this.course = course;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
