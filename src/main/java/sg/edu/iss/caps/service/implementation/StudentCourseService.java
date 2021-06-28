package sg.edu.iss.caps.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.Student_Course;
import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.repo.CourseRepository;
import sg.edu.iss.caps.repo.StudentCourseRepository;
import sg.edu.iss.caps.service.interfaces.IStudentCourse;

@Service public class StudentCourseService implements IStudentCourse {

	  @Autowired CourseRepository crepo;
	  @Autowired StudentCourseRepository screpo;
	  
	  @Transactional
		public void addStudentToCourse(Course course, User user) {
			Student_Course scourse = new Student_Course();
			scourse.setCourse(course);
			scourse.setUser(user);
			screpo.save(scourse);
		}
	  
	  @Transactional
		public void removeStudentFromCourse(Course course, User user) {
		  Student_Course current = screpo.findStudentCourseByCourseAndUser(course, user).get(0);
		  screpo.delete(current);
		}

	  @Transactional
		public void addLecturerToCourse(Course course, User user) {
			// TODO Auto-generated method stub
			
		}

	  @Transactional
		public void removeLecturerFromCourse(Course course, User user) {
			// TODO Auto-generated method stub
			
		}

	  @Transactional
	  public List<User> listStudentsInCourse(Course course){
		  return screpo.listStudentsInCourse(course);
	  }
}
