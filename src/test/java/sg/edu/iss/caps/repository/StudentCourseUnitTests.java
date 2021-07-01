package sg.edu.iss.caps.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import sg.edu.iss.caps.T11CapsApplication;
import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.Student_Course;
import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.repo.CourseRepository;
import sg.edu.iss.caps.repo.StudentCourseRepository;
import sg.edu.iss.caps.repo.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = T11CapsApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentCourseUnitTests {
	@Autowired
	StudentCourseRepository screpo;
	@Autowired
	UserRepository urepo;
	@Autowired
	CourseRepository crepo;
	
	@Test
	@Order(1)
	public void testFindStudentCoursesByStudent() {
		
		String img="https://www.bootdey.com/img/Content/avatar/avatar10.png";
		String email="27031981@gmail.com";		
		User student= new User("JJ","Lin",email,"123",img,27031981);
		urepo.save(student);
		
		List<Student_Course> saved=screpo.findStudentCoursesByStudent(student);
		assertTrue(saved.size() > 0);
		}
	@Test
	@Order(2)
	public void testFindStudentCourseByCourseAndUser() {
		
		String img="https://www.bootdey.com/img/Content/avatar/avatar10.png";
		String email="23071978@gmail.com";		
		User user= new User("Yanzi","Sun",email,"123",img,23071978);
		urepo.save(user);
		
		String description="teach students how to use Project";
		long startDate=02072021;
		long endDate=03072021;
		String code="4111";
		Course course= new Course("SA4111",description,1,6,startDate,endDate,code);
		crepo.save(course);
		
		List<Student_Course> saved=screpo.findStudentCourseByCourseAndUser(course, user);
		
		assertTrue(saved.size()  > 0);
		}
	
	@Test
	@Order(3)
	public void testListStudentsInCourse() {
		
		String description="teach students how to use WhiteStar";
		long startDate=01072021;
		long endDate=05072021;
		String code="4113";
		Course course= new Course("SA4113",description,2,4,startDate,endDate,code);
		crepo.save(course);
		
		List<User> saved=screpo.listStudentsInCourse(course);
		
		assertTrue(saved.size()  > 0);
	}
	@Test
	@Order(4)
	public void listStudentsGradesInCourse() {
		
		String description="teach students how to use Viso";
		long startDate=01062021;
		long endDate=05062021;
		String code="4114";
		Course course= new Course("SA4114",description,2,10,startDate,endDate,code);
		crepo.save(course);
		
		List<Student_Course> saved=screpo.listStudentsGradesInCourse(course);
		assertTrue(saved.size()  > 0);
	}

}
