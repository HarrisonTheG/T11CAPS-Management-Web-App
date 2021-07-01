package sg.edu.iss.caps.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import sg.edu.iss.caps.repo.CourseRepository;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = T11CapsApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class CourseUnitTests {

	@Autowired
	private CourseRepository crepo;	
	
	@Test
	@Order(1)
	public void testCreateCourse() {
		  String description="teach students how to use github";
		  long startDate=01072021;
		  long endDate=07072021;
		  String code="4110";
		  Course c= new Course("SA4110",description,5,8,startDate,endDate,code);	   
		  Course saved=crepo.save(c);
		  assertNotNull(saved);
		}
	@Test
	@Order(2)
	public void testSearchCourseByKeyword() {
		String keywords="java";
		List<Course> saved=crepo.search(keywords);
		assertTrue(saved.size()>0);
	}
	@Test
	@Order(3)
	public void testFindCourseByUser() {
	    Integer userId=6;
		List<Course> saved=crepo.findCoursesByUser(userId);
		assertTrue(saved.size()>0);
	}
	@Test
	@Order(4)
	public void testFindallUsersByCourse() {
		Integer courseId=2;
		List<Course> saved=crepo.findallUsersByCourse(courseId);
		assertTrue(saved.size()>0);
	}
			
	
}
