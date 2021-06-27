package sg.edu.iss.caps.service.interfaces;

import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.User;

public interface IStudentCourse {
	public void addStudentToCourse(Course course, User user);
	public void removeStudentFromCourse(Course course, User user);
	public void addLecturerToCourse(Course course, User user);
	public void removeLecturerFromCourse(Course course, User user);
}
