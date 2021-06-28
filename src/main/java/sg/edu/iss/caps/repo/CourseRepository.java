
  package sg.edu.iss.caps.repo;
  
  import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import
  org.springframework.stereotype.Repository;
  
  import sg.edu.iss.caps.model.Course;
  
  @Repository public interface CourseRepository extends JpaRepository<Course,
  Integer> {
  
	  @Query("SELECT c FROM Course c WHERE CONCAT(c.id, ' ', c.code, ' ', c.description, ' ', c.name) LIKE %?1%")
		public List<Course> search(String keyword);
	  
  }
 
