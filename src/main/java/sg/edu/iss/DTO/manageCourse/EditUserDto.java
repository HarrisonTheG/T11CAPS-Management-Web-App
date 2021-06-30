package sg.edu.iss.DTO.manageCourse;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.RoleType;
import sg.edu.iss.caps.model.Student_Course;
import sg.edu.iss.caps.model.User;


public class EditUserDto {
	
	
	private int id;
	@NotBlank
	private String code;
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
	private String enrollmentDate;
	private RoleType role;
	
	private List<Course> Course;
	
	public EditUserDto(@NotBlank String firstname, @NotBlank String surname, @Email String email, @NotBlank String password,
			@URL String imgUrl, String enrollmentDate, @NotBlank String code, RoleType role) {
		super();
		this.firstname = firstname;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.imgUrl = imgUrl;
		this.enrollmentDate = enrollmentDate;
		this.role = role;
		this.code = code;
	}
	
	public EditUserDto() {
		super();
	}

	

	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}

	
	public List<Course> getCourse() {
		return Course;
	}


	public void setCourse(List<Course> Course) {
		this.Course = Course;
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
	public String getEnrollmentDate() {
		return enrollmentDate;
	}
	
	public void setEnrollmentDate(String enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

}
