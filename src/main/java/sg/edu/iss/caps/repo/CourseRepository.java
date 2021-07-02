
  package sg.edu.iss.caps.repo;
  
  import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import
  org.springframework.stereotype.Repository;
  
  import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.User;
  
  @Repository public interface CourseRepository extends JpaRepository<Course,
  Integer> {
	  
	  
  
	  @Query("SELECT c FROM Course c WHERE CONCAT(c.id, ' ', c.code, ' ', c.description, ' ', c.name) LIKE %?1%")
		public List<Course> search(String keyword);
	  
	  @Query("SELECT c FROM Course c JOIN c.User u WHERE u.id = :id ")
	  	public List<Course> findCoursesByUser(Integer id);
	    
	  @Query("SELECT c FROM Course c JOIN c.User u WHERE c.id=:id")
	  	public List<Course> findallUsersByCourse(Integer id);

	  @Query("SELECT c.User FROM Course c where c.id=:id")
	  public List<User> findLecturerBycourse(@Param("id") Integer id);
  }
 
