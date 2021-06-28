package sg.edu.iss.caps.service.interfaces;

import java.util.List;

import sg.edu.iss.caps.model.Course;

public interface ICourse {
	public Course findCourseById(Integer id);
	public List<Course> listAll(String keyword);
}
