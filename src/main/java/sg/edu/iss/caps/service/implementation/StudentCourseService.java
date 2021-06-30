package sg.edu.iss.caps.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.RoleType;
import sg.edu.iss.caps.model.Student_Course;
import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.repo.CourseRepository;
import sg.edu.iss.caps.repo.StudentCourseRepository;
import sg.edu.iss.caps.repo.UserRepository;
import sg.edu.iss.caps.service.interfaces.IStudentCourse;
import sg.edu.iss.caps.utility.UtilityManager;

@Service public class StudentCourseService implements IStudentCourse {

	  @Autowired CourseRepository crepo;
	  @Autowired StudentCourseRepository screpo;
	  @Autowired UserRepository urepo;
	  

		@Override
		public List<Student_Course> findStudentCoursesByStudentId(int id) {
			
			User student = urepo.findById(id).orElse(null); 
			if(UtilityManager.checkIdentity(student, "student") != "error") {
			List<Student_Course> studentCourses = screpo.findStudentCoursesByStudent(student);
			return studentCourses;
			}
			
			return null;
		}
		

	  
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
	  
	  @Transactional
	  public List<Student_Course> listStudentsGradesInCourse(Course course){
		  return screpo.listStudentsGradesInCourse(course);
	  }
	  
	  @Transactional
	  public void deleteStudentCourse(Course course){
		  List<Student_Course> todelete=screpo.listStudentsGradesInCourse(course);
		  if(todelete!=null) {
			  for(Student_Course s:todelete) {
			  }			  
		  }

	  }

	  @Override
	  @Transactional
	  public void  editStudentsGradesInCourse(Student_Course selectedStudentCourse){
		  Student_Course StudentCourse = screpo.findById(selectedStudentCourse.getId()).get();
		  StudentCourse.setGrade(selectedStudentCourse.getGrade());
		  screpo.save(StudentCourse);
	  }
	  
	  @Transactional
		public Student_Course findStudentCourseById(Integer id) {
			 return screpo.getById(id);
		}










}
