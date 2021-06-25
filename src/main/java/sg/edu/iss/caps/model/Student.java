package sg.edu.iss.caps.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity @DiscriminatorValue("student")
public class Student extends User {

	@OneToMany(mappedBy = "student", 
			cascade = CascadeType.ALL,
	        orphanRemoval = true)
	private List<Student_Course> studentCourses;
	

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}


	public List<Student_Course> getStudentCourses() {
		return studentCourses;
	}


	public void setStudentCourses(List<Student_Course> studentCourses) {
		this.studentCourses = studentCourses;
	}


	public Student(int id, String firstname, String surname, String email, String password, String imgUrl,
			long enrollmentDate) {
		super(id, firstname, surname, email, password, imgUrl, enrollmentDate);
	}

	
}
