package sg.edu.iss.caps.model;

import java.beans.Transient;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

@Entity
public class User {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Size(max=30) @NotBlank
	private String firstname;
	@Size(max=30) @NotBlank
	private String surname;
	@Email
	private String email;
	@NotBlank
	private String password;
	@URL
	private String imgUrl;
	private long enrollmentDate;
	private RoleType role;
	
	@ManyToMany(mappedBy="User")
	private List<Course> Course;
	
	public User(int id, String firstname, String surname, String email, String password, String imgUrl, long enrollmentDate) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.imgUrl = imgUrl;
		this.enrollmentDate = enrollmentDate;
	}

	public User(String firstname, String surname, String email, String password, String imgUrl, long enrollmentDate) {
		super();
		this.firstname = firstname;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.imgUrl = imgUrl;
		this.enrollmentDate = enrollmentDate;
	}
	
	public User() {
		super();
	}
	
	@Transient
	public String getDiscriminatorValue() {
		return this.getClass().getAnnotation(DiscriminatorValue.class).value();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getEnrollmentDate() {
		return enrollmentDate;
	}
	public void setEnrollmentDate(long enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", surname=" + surname + ", email=" + email + "]";
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
}
