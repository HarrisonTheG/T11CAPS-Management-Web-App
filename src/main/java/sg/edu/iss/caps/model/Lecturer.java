package sg.edu.iss.caps.model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity @DiscriminatorValue("lecturer")
public class Lecturer extends User {

	@ManyToMany
	private List<Course> courses;
	
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Lecturer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Lecturer(int id, String firstname, String surname, String email, String password, String imgUrl,
			long enrollmentDate) {
		super(id, firstname, surname, email, password, imgUrl, enrollmentDate);
		// TODO Auto-generated constructor stub
	}

	public Lecturer(String firstname, String surname, String email, String password, String imgUrl,
			long enrollmentDate) {
		super(firstname, surname, email, password, imgUrl, enrollmentDate);
		// TODO Auto-generated constructor stub
	}
	
	

}
