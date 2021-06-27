package sg.edu.iss.caps.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.iss.caps.model.Student_Course;

public interface StudentCourseRepository extends JpaRepository<Student_Course, Integer> {

	@Query("Select sc from Student_Course s where s.Course = ?1 and s.User = ?2")
	   public Student_Course findStudentCourse(Integer course_id, Integer student_id);
	
}
