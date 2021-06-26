 package sg.edu.iss.caps.repo;
  
  import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import
  org.springframework.stereotype.Repository;

import sg.edu.iss.caps.model.User;
  
  
  
  @Repository public interface LecturerRepository extends
  JpaRepository<User, Integer>{
	  
	  @Query("SELECT u FROM User u WHERE CONCAT(u.id, ' ', u.firstname, ' ', u.surname, ' ', u.email) LIKE %?1%")

	  public List<User> search(String keyword);
  
 }
 