package sg.edu.iss.caps.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.caps.model.Student_Course;

public interface StudentCourseRepository extends JpaRepository<Student_Course, Integer> {

}
