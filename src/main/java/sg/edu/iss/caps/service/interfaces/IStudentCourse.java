package sg.edu.iss.caps.service.interfaces;

import java.util.List;
import java.util.Optional;

import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.Student_Course;
import sg.edu.iss.caps.model.User;

public interface IStudentCourse {
	

	public List<Student_Course> findStudentCoursesByStudentId(int id);
	
	public void addStudentToCourse(Course course, User user);
	public void removeStudentFromCourse(Course course, User user);
	public void addLecturerToCourse(Course course, User user);
	public void removeLecturerFromCourse(Course course, User user);
	public List<User> listStudentsInCourse (Course course);
	public List<Student_Course> listStudentsGradesInCourse(Course course);
	public void editStudentsGradesInCourse(Student_Course studentCourse);
	public Student_Course findStudentCourseById(Integer id);
}
