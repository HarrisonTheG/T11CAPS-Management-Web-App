package sg.edu.iss.caps.repo;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.caps.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	

	User findByEmailAndPassword(@Valid String email, String password);
	
	@Query("Select s from User u where s.id = :id and s.role = STUDENT")
	   public User findStudent(@Param("id") Integer id);
	
	@Query("Select s from User u where s.id = :id and s.role = LECTURER")
	   public User findLecturer(@Param("id") Integer id);
}
