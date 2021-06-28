package sg.edu.iss.caps.service.interfaces;

import java.util.List;

import java.util.Optional;

import sg.edu.iss.caps.model.Course;

public interface ICourse {
	
	public List<Course> listAllCourses();
	
	public Optional<Course> findCourse(int id);
  public Course findCourseById(Integer id);
	public List<Course> listAll(String keyword);

}
