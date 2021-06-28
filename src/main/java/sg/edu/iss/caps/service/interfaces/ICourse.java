package sg.edu.iss.caps.service.interfaces;

import java.util.List;

import java.util.Optional;

import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.User;

public interface ICourse {
	
	public List<Course> listAllCourses();
	
	public Optional<Course> findCourse(int id);
	
	public void edit(Course course);
  public Course findCourseById(Integer id);
	public Course findCourseById(Integer id);

	//search bar keyword
	public List<Course> listAll(String keyword);
	
	public List<Course> findCourseByLecturerId(Integer id);
	
	public void addLecturerToCourse(List<User> lecturers, Integer cid);
	public void deleteLecturerFromCourse(User lecturer, Integer cid);

}
