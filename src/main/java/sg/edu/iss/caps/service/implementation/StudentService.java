
package sg.edu.iss.caps.service.implementation;

import javax.persistence.LockModeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.Student_Course;
import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.repo.CourseRepository;
import sg.edu.iss.caps.repo.StudentCourseRepository;
import sg.edu.iss.caps.repo.UserRepository;
import sg.edu.iss.caps.service.interfaces.IStudent;

@Service
public class StudentService implements IStudent {
	
	@Autowired StudentCourseRepository screpo;
	@Autowired UserRepository urepo;
	@Autowired CourseRepository crepo;
	
	@Override
	@Transactional @Lock(LockModeType.PESSIMISTIC_WRITE)
	public void enrollStudentToCourse(int studentId, int courseId) {
		
		User student = urepo.findById(studentId).orElse(null);
		Course course = crepo.findById(courseId).orElse(null);
		
		Student_Course newSC = new Student_Course();
		newSC.setCourse(course);
		newSC.setUser(student);
		//newSC.setGrade(0.0);
		screpo.save(newSC);
	}
	
}
 
