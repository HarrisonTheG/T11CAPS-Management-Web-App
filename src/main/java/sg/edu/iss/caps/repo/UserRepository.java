package sg.edu.iss.caps.repo;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.model.RoleType;

public interface UserRepository extends JpaRepository<User,Integer>{
	
	Optional<User> findByEmail(String userName); //tell jpa that this service needs implementation in users.
	//method is given a username, JPA do a method username, finds a given user.


	User findByEmailAndPassword(@Valid String email, String password);
	

	@Query("SELECT u FROM User u WHERE CONCAT(u.id, ' ', u.firstname, ' ', u.surname, ' ', u.email) LIKE %?1%")
	public List<User> search(String keyword);

	@Query("Select u from User u where u.id = :id and u.role = :role")
	   public User findUserByRoleType(@Param("id") Integer id, RoleType role);
	
//	@Query("Select u from User u where u.id = ?1 and u.role = ?2")
//	   public User findStudent(@Param("id") Integer id, @Param("role") RoleType role);

//	@Query("Select u from User u where u.id = :id and u.role = :role")
//	   public User findLecturer(@Param("id") Integer id, RoleType role);

	@Query("SELECT u FROM User u WHERE u.role = STUDENT AND CONCAT(u.id, ' ', u.firstname, ' ', u.surname, ' ', u.email) LIKE %?1%")
	public List<User> findStudents(String keyword);
	
	@Query("SELECT u FROM User u WHERE u.role = :role")
	public List<User> listAllStudents(@Param("role") RoleType role);
}
