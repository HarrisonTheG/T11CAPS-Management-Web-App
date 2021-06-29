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
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import sg.edu.iss.caps.model.Student_Course;
import sg.edu.iss.caps.model.User;


public class EditCourseDto {
	
	
	private int id;
	@NotBlank
	private String code;
	@NotBlank
	private String name;
	private String description;
	@Range(min=1, max=5)
	private int credit;
	private int maxSize;
	private String startDate;
	private String endDate;

	private List<Student_Course> studentCourses;
	
	private List<User> User;
	
	public EditCourseDto(@NotBlank String name, String description, @Range(min = 1, max = 5) int credit, int maxSize,
			String startDate, String endDate, @NotBlank String code) {
		super();
		this.name = name;
		this.description = description;
		this.credit = credit;
		this.maxSize = maxSize;
		this.startDate = startDate;
		this.endDate = endDate;
		this.code = code;
	}
	
	public EditCourseDto() {
		super();
	}

	

	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}

	
	public List<Student_Course> getStudentCourses() {
		return studentCourses;
	}


	public void setStudentCourses(List<Student_Course> studentCourses) {
		this.studentCourses = studentCourses;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public List<User> getUser() {
		return User;
	}


	public void setUser(List<User> user) {
		User = user;
	}

}
