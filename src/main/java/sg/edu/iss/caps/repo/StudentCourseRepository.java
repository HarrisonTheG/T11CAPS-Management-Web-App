package sg.edu.iss.caps.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.Student_Course;
import sg.edu.iss.caps.model.User;

public interface StudentCourseRepository extends JpaRepository<Student_Course, Integer> {

	@Query("Select s from Student_Course s where s.course = ?1 and s.user = ?2")
	public List<Student_Course> findStudentCourseByCourseAndUser(Course course, User user);

	@Query("Select s.user from Student_Course s WHERE s.course = ?1")
	public List<User> listStudentsInCourse(Course course);
}
